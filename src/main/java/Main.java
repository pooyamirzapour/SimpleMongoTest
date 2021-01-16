import com.mongodb.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        MongoClient mongoClient;
        MongoClientOptions options = MongoClientOptions.builder().connectTimeout(50000).build();
        ArrayList<ServerAddress> serverAddresses = new ArrayList();
        serverAddresses.add(new ServerAddress("localhost", 27017));
        List<MongoCredential> credentialsList = new ArrayList();
        MongoCredential credential = MongoCredential.createCredential("admin","test","password".toCharArray());
        credentialsList.add(credential);
        mongoClient = new MongoClient(serverAddresses, options);
        Datastore datastore;
        try {
            datastore = new Morphia().createDatastore(mongoClient, "test");
            datastore.ensureCaps();
            datastore.ensureIndexes();
            Book book = new Book("mongo", "me");
            datastore.save(book);
        } catch (MongoException e) {
            e.printStackTrace();
        }

    }
}
