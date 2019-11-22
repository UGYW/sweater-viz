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

        DynamicAnalysis dynamic_analysis = new DynamicAnalysis();
        dynamic_analysis.conductAnalysis();
        MainFrame mf = new MainFrame("SweaterViz");
    }
}
