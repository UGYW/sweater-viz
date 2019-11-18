import analysis.Analysis;
import analysis.DynamicAnalysis;
import analysis.StaticAnalysis;
import injector.Injector;
import ui.MainFrame;

import java.io.IOException;

public class SweaterViz {
    public static void main(String[] args) {
        Injector injector = new Injector();
        try
        {
            injector.injectCode("./input/HttpDownloader.java", "someOutput.java");
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        Analysis static_analysis = new StaticAnalysis();
        Analysis dynamic_analysis = new DynamicAnalysis();

        MainFrame example = new MainFrame("Gantt Chart Example");
    }
}
