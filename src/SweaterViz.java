import ui.MainFrame;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class SweaterViz {
    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                MainFrame example = new MainFrame("Gantt Chart Example");
                example.setSize(800, 400);
                example.setLocationRelativeTo(null);
                example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                example.setVisible(true);
        });
    }
}
