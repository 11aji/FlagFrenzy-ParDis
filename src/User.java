import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password; // Add password field
    private int highScore;

    public User(String username, String password) { // Update constructor
        this.username = username;
        this.password = password;
        this.highScore = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password; // Add getter for password
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}

