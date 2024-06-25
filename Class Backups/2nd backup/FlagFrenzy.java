import javax.swing.*;

public class FlagFrenzy {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Flag Frenzy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        MainPanel mainPanel = new MainPanel();
        frame.add(mainPanel);

        frame.setVisible(true);
    }
}
