import analysis.*;
import ui.MainFrame;

import java.util.List;

public class SweaterViz {
    public static void main(String[] args) {
//        Injector injector = new Injector();
//        try
//        {
//            injector.injectCode("./input/HttpDownloader.java", "someOutput.java");
//        }
//        catch (IOException e)
//        {
//            System.out.println(e);
//        }

        Analysis static_analysis = new StaticAnalysis();
        Analysis dynamic_analysis = new DynamicAnalysis();
        dynamic_analysis.conductAnalysis();

        //Parse Dataset
        Analytics.getInstance().parseDataset();

        ThreadInfoGenerator tiGenerator = new ThreadInfoGenerator();
        tiGenerator.generateThreadInfos();
        List<ThreadInfo> infos = tiGenerator.getThreadInfos(); //UI needs to get this.

        MainFrame example = new MainFrame("Gantt Chart Example");
    }
}
