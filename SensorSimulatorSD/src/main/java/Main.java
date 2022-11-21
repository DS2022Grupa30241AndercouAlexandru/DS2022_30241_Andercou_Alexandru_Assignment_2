import DataReading.RabbitMQSender;
import lombok.Value;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassLoader loader = Main.class.getClassLoader();
        System.out.println(loader.getResource("Main.class"));

        FileReader reader=new FileReader("src/main/resources/aplication.propeties");
        Properties p=new Properties();
        p.load(reader);
        DataProcessor dp;
        dp=  new  DataProcessor(Long.valueOf(p.getProperty("DEVICE_ID")));


    }
}