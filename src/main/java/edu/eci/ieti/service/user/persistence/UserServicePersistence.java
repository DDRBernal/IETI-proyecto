package edu.eci.ieti.service.user.persistence;

import com.mongodb.*;
import com.mongodb.client.*;
import edu.eci.ieti.repository.Componente;
import edu.eci.ieti.repository.Tarjeta_de_video;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;
import edu.eci.ieti.repository.user.User;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class UserServicePersistence {
    private static HashMap<String, User> users = new HashMap<String,User>();

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public UserServicePersistence(){
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
    public void save(User user) {
        MongoDatabase database = mongoClient.getDatabase("proyecto-ieti");
        MongoCollection<Document> componentes = database.getCollection("collection-proyecto-ieti");
        FindIterable<Document> iterable = componentes.find();
        MongoCursor<Document> cursor = iterable.iterator();
        Document data_collection = new Document("_id", new ObjectId());
        ArrayList<Componente> data = getData();
        data_collection.append(user.getName(), user.getId());
        data_collection.append(String.valueOf(user.getEmail()), user.getId());
        componentes.insertOne(data_collection);
    }

    public static ArrayList<Componente> getData(){
        ArrayList<Componente> data = new ArrayList<>();
        MongoCollection<Document> customers = database.getCollection("collection-proyecto-ieti");
        FindIterable<Document> iterable = customers.find();
        MongoCursor<Document> cursor = iterable.iterator();
        for (Document d : iterable) {
            Componente componente = null;
            try {
                JSONObject jsonObject = new JSONObject(d.toJson());
                String name = jsonObject.get("name").toString();
                double boots = Double.parseDouble(jsonObject.get("boost_lock").toString());
                double core = Double.parseDouble(jsonObject.get("core_lock").toString());
                int largo = Integer.parseInt(jsonObject.get("largo").toString());
                int memoria = Integer.parseInt(jsonObject.get("memoria").toString());
                String precio = jsonObject.get("precio").toString();
                String procesador = jsonObject.get("procesador").toString();
                String valoracion = jsonObject.get("valoracion").toString();
                componente = new Tarjeta_de_video(name,procesador,memoria,core,boots,largo);
                data.add(componente);
            }catch (JSONException ignored){
            }
        }
        return data;
    }

    public User findById(String id) {
        return users.get(id);
    }

    public List<User> all(){
        return new ArrayList<>(users.values());
    }

    public void deleteById(String id) {
        users.remove(id);
    }

    public User update(User user, String userId) {
        users.remove(userId);
        users.put(userId,user);
        return user;
    }
}
