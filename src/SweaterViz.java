import analysis.Analysis;
import analysis.DynamicAnalysis;
import analysis.StaticAnalysis;
import ui.MainFrame;

public class SweaterViz {
    public static void main(String[] args) {
        Analysis static_analysis = new StaticAnalysis();
        Analysis dynamic_analysis = new DynamicAnalysis();

        MainFrame example = new MainFrame("Gantt Chart Example");
    }
}
