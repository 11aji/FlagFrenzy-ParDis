import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static final String DATABASE_NAME = "userDB";
    private static final String COLLECTION_NAME = "users";
    private Map<String, User> users;
    private MongoCollection<Document> userCollection;

    public UserManager() {
        users = new HashMap<>();
        connectToDatabase();
    }

    private void connectToDatabase() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        userCollection = database.getCollection(COLLECTION_NAME);
    }

    public boolean registerUser(String username, String password) {
        if (users.containsKey(username) || findUser(username) != null) {
            return false;
        }
        User user = new User(username, password);
        users.put(username, user);
        saveUser(user);
        return true;
    }

    public User loginUser(String username, String password) {
        User user = loadUser(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    private void saveUser(User user) {
        Document doc = new Document("username", user.getUsername())
                .append("password", user.getPassword())
                .append("highScore", user.getHighScore())
                .append("mediumUnlocked", user.isMediumUnlocked())
                .append("hardUnlocked", user.isHardUnlocked());
        userCollection.insertOne(doc);
    }

    private User loadUser(String username) {
        Document query = new Document("username", username);
        Document doc = userCollection.find(query).first();
        if (doc != null) {
            User user = new User(doc.getString("username"), doc.getString("password"));
            user.setHighScore(doc.getInteger("highScore"));
            user.setMediumUnlocked(doc.getBoolean("mediumUnlocked"));
            user.setHardUnlocked(doc.getBoolean("hardUnlocked"));
            return user;
        }
        return null;
    }

    public void updateUserProgress(User user) {
        Document query = new Document("username", user.getUsername());
        Document update = new Document("$set", new Document("highScore", user.getHighScore())
                .append("mediumUnlocked", user.isMediumUnlocked())
                .append("hardUnlocked", user.isHardUnlocked()));
        userCollection.updateOne(query, update);
    }

    private Document findUser(String username) {
        return userCollection.find(new Document("username", username)).first();
    }
}
