package analysis;

import injector.Injector;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;

public class DynamicAnalysis extends Analysis {
    String injectImport="// #### import library #####\n" +
            "import java.util.*; \n" +
            "import java.io.BufferedWriter;\n" +
            "import java.io.FileWriter;";

    String injectBeforeStart="// ##################### code to add ##########################\n" +
            "\t\tBufferedWriter writer = new BufferedWriter(new FileWriter(\"analysisData.txt\"));\n" +
            "\t\twriter.write(\"start time:\" + System.currentTimeMillis()+\"\\n\");\n" +
            "\t\tThread monitor = new Thread(() -> {\n" +
            "\t\t\t\ttry{\n" +
            "\t\n" +
            "\t\t\t\t\twriter.write(\"### momitor thread is running\\n\");\n" +
            "\t\t\t\t\n" +
            "\t\t\t\t\tSet<Thread> threadSet = Thread.getAllStackTraces().keySet();\n" +
            "\t\t\t\t\twhile(true){\n" +
            "\t\t\t\t\t\twriter.write(\"### threadSet size: \"+threadSet.size()+\" time: \"+System.currentTimeMillis()+\"\\n\");\n" +
            "\t\t\t\t\t\tfor(Thread t: threadSet){\n" +
            "\t\t\t\t\t\t\twriter.write(\"### thread info \"+t.getId()+\" name \"+t.getName()+\" status \"+t.getState()+\"\\n\");\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\tThread.sleep(100);\n" +
            "\t\t\t\t\t\tthreadSet = Thread.getAllStackTraces().keySet();\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}catch (InterruptedException e) {\n" +
            "\t\t\t\t\tSystem.out.println(\"Interrupted exception\"+e);\n" +
            "\t\t\t   }\n" +
            "\t\t\t    catch(IOException ioe){\n" +
            "\t\t\t\t\tSystem.out.println(\"IO exception\"+ioe);\n" +
            "\t\t\t\t   \n" +
            "\t\t\t   }\n" +
            "\t\t\t\t\n" +
            "\t\t\t});\n" +
            "\t\t\tmonitor.setDaemon(true);\n" +
            "\t\t\tmonitor.start();\n" +
            "\n" +
            "\t\t\n" +
            "\n" +
            "\t\t// ############################# end of code to add ###########################";

    String injectBeforeFinish= "writer.close();";

    Injector injector;

    String outputDir="./";

    public DynamicAnalysis() {
        injector = new Injector(injectBeforeStart,injectBeforeFinish,injectImport);
    }

    @Override
    public void conductAnalysis() {
        System.out.println("Starting dynamic analysis...");
        System.out.println("injection ongoing...");
        try {
            injector.injectCode("./input/HttpDownloader.java", outputDir+"HttpDownloader.java");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("injection finished!");

        System.out.println("start compiling injected file...");
        String fileToCompile = outputDir+"HttpDownloader.java";
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        FileOutputStream errorStream = null;
        try {
            errorStream = new FileOutputStream(outputDir+"errors.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int compilationResult = compiler.run(null, null, errorStream,fileToCompile );
        if(compilationResult == 0){
            System.out.println(fileToCompile+" compile successful!");
        } else {
            System.out.println(fileToCompile+" compile failed! error can be found in errors.txt");
        }

        System.out.println("start running compiled java file..");
        String fileToRun = outputDir+"HttpDownloader";
        try {
//            runProcess("cd");
            runProcess("java -version");
            runProcess("java HttpDownloader");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("done!");

    }
    private void printLines(InputStream ins) throws IOException {
        String line = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(ins));
        while ((line = in.readLine()) != null){
            System.out.println(line);
        }
    }

    private void runProcess(String cmd) throws IOException{
        Process pro = Runtime.getRuntime().exec(cmd);
        System.out.print("stdout: ");
        printLines(pro.getInputStream());
        System.out.println("stderr: ");
        printLines(pro.getErrorStream());
    }

}
