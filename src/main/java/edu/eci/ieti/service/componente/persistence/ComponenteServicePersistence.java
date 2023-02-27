package edu.eci.ieti.service.componente.persistence;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import edu.eci.ieti.repository.Componente;
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
        databases.forEach(db -> System.out.println(db.toJson()));
        database = mongoClient.getDatabase("Cluster0");
    }

    public void save(Componente component) {
        MongoDatabase database = mongoClient.getDatabase("Cluster0");
        MongoCollection<Document> componentes = database.getCollection("collection-proyecto-ieti");
        FindIterable<Document> iterable = componentes.find();
        MongoCursor<Document> cursor = iterable.iterator();
        Document data_collection = new Document("_id", new ObjectId());
        ArrayList<String> data = getData();
        data_collection.append("Componente"+data.size(), component.getId());
        componentes.insertOne(data_collection);
    }

    public static ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        MongoCollection<Document> customers = database.getCollection("collection-proyecto-ieti");
        FindIterable<Document> iterable = customers.find();
        MongoCursor<Document> cursor = iterable.iterator();
        for (Document d : iterable) {
            System.out.println(d);
            data.add(d.toString());
        }
        return data;
    }

    public Componente findById(String id) {
        return componentes.get(id);
    }

    public List<Componente> all(){
        return new ArrayList<>(componentes.values());
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
