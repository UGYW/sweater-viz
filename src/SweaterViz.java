import ui.MainFrame;

import javax.swing.SwingUtilities;

public class SweaterViz {
    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                MainFrame example = new MainFrame("Gantt Chart Example");
        });
    }
}
