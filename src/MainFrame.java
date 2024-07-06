import java.awt.*;
import javax.swing.*;


public class MainFrame extends JFrame {



    public MainFrame() {
        setTitle("Flag Frenzy");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());
        setResizable(false); // Disable resizing


        MainPanel mainPanel = new MainPanel();
        add(mainPanel, "MAIN_PANEL");

        // Center the frame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}

