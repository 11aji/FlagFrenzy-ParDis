import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Flag Frenzy");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        MainPanel mainPanel = new MainPanel();
        add(mainPanel, "MAIN_PANEL");

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
