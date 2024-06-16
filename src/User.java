public class User {
    private String username;
    private int highScore;

    public User(String username) {
        this.username = username;
        this.highScore = 0;
    }

    public String getUsername() {
        return username;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
