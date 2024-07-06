import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private int highScore;
    private boolean mediumUnlocked;
    private boolean hardUnlocked;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.highScore = 0;
        this.mediumUnlocked = false;
        this.hardUnlocked = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public boolean isMediumUnlocked() {
        return mediumUnlocked;
    }

    public void setMediumUnlocked(boolean mediumUnlocked) {
        this.mediumUnlocked = mediumUnlocked;
    }

    public boolean isHardUnlocked() {
        return hardUnlocked;
    }

    public void setHardUnlocked(boolean hardUnlocked) {
        this.hardUnlocked = hardUnlocked;
    }
}
