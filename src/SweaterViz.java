import analysis.*;

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

        //TODO: Parse here.
        ThreadInfoGenerator tiGenerator = new ThreadInfoGenerator();
        tiGenerator.generateThreadInfos();
        List<ThreadInfo> infos = tiGenerator.getThreadInfos(); //UI needs to get this.
        int a = 1;
//        MainFrame example = new MainFrame("Gantt Chart Example");
    }
}
