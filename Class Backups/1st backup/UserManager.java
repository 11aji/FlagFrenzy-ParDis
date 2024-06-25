import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static final String DATA_FILE = "users.dat";
    private Map<String, User> users;

    public UserManager() {
        users = new HashMap<>();
        loadUsers();
    }

    public boolean registerUser(String username) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new User(username));
        saveUsers();
        return true;
    }

    public User loginUser(String username) {
        return users.get(username);
    }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            users = (Map<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
