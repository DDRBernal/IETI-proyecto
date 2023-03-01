package edu.eci.ieti.service.componente.persistence;

import com.mongodb.*;
import com.mongodb.client.*;
import edu.eci.ieti.repository.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class ComponenteServicePersistence {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    private static HashMap<String, Componente> componentes = new HashMap<String,Componente>();

    public ComponenteServicePersistence(){
        String connstr ="mongodb+srv://admin:admin@cluster0.85ubqzs.mongodb.net/?retryWrites=true&w=majority";
        ConnectionString connectionString = new ConnectionString(connstr);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        mongoClient = MongoClients.create(settings);
        List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
        List<Document> lastElementsArray = databases.subList(Math.max(databases.size() - 1, 0), databases.size());
        databases.forEach(db -> System.out.println("sds" + db.toJson()));
        database = mongoClient.getDatabase("proyecto-ieti");
    }

    public void save(Componente component) {
        MongoDatabase database = mongoClient.getDatabase("proyecto-ieti");
        MongoCollection<Document> componentes = database.getCollection("collection-proyecto-ieti");
        FindIterable<Document> iterable = componentes.find();
        MongoCursor<Document> cursor = iterable.iterator();
        Document data_collection = new Document("_id", new ObjectId());
        ArrayList<Componente> data = getData();
        data_collection.append(component.getNombre(), component.getId());
        data_collection.append(String.valueOf(component.getPrecio()), component.getId());
        componentes.insertOne(data_collection);
    }

    public static ArrayList<Componente> getData(){
        ArrayList<Componente> data = new ArrayList<>();
        MongoCollection<Document> customers = database.getCollection("collection-proyecto-ieti");
        FindIterable<Document> iterable = customers.find();
        for (Document d : iterable) {
            try {
                Componente componente = null;
                JSONObject jsonObject = new JSONObject(d.toJson());
                String name = jsonObject.get("name").toString();
                String className = jsonObject.get("class_name").toString();
                switch (className) {
                    case "Tarjeta_de_video" -> componente = new Tarjeta_de_video(name).createComponente(jsonObject);
                    case "CPU" -> componente = new CPU(name).createComponente(jsonObject);
                    case "RAM" -> componente = new RAM(name).createComponente(jsonObject);
                    case "Board" -> componente = new Board(name).createComponente(jsonObject);
                    case "Carcasa" -> componente = new Carcasa(name).createComponente(jsonObject);
                    case "Disco_Duro" -> componente = new Disco_Duro(name).createComponente(jsonObject);
                    case "Fuente_de_poder" -> componente = new Fuente_de_poder(name).createComponente(jsonObject);
                }
                if (componente != null) data.add(componente);
            }catch (JSONException err){
            }
        }
        return data;
    }

    public Componente findById(String id) {
        return componentes.get(id);
    }

    public List<Componente> all(){
        return getData();
    }

    public void deleteById(String id) {
        componentes.remove(id);
    }

    public Componente update(Componente componente, String userId) {
        componentes.remove(userId);
        componentes.put(userId,componente);
        return componente;
    }
}
