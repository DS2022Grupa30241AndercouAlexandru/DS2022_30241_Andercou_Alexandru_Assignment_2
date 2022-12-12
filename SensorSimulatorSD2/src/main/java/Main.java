import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassLoader loader = Main.class.getClassLoader();
        System.out.println(loader.getResource("Main.class"));
        Properties p=new Properties();

        ClassLoader cl=Thread.currentThread().getContextClassLoader();
        try {
            p.load( cl.getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DataProcessor dp;
        dp=  new  DataProcessor(Long.valueOf(p.getProperty("DEVICE_ID")));


    }
}