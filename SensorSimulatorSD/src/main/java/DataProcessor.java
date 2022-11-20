import DataReading.DataCsvReader;
import DataReading.MessageToSend;
import DataReading.RabbitMQSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
public class DataProcessor{

    long delay; // delay in milliseconds
    LoopTask task;
    Timer timer;

    RabbitMQSender mqSender;

    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
    private static final Integer NUMBER_READINGS= 6;
    private Integer currentReadingIndex;
    private Integer numberReadingsForAverage;
    private List<List<String>> data;

    private Long device_id;

    private Float valueToSend;



    DataProcessor(Long device_id)
    {
         this.device_id=device_id;
         //10*6*
        delay =100 * 1000;
        mqSender = new  RabbitMQSender();

        task = new LoopTask();
        timer = new Timer("TaskName");
        DataCsvReader dataReader=new    DataCsvReader();
        data=dataReader.readDataCsv();
        numberReadingsForAverage=Integer.valueOf(0);
        currentReadingIndex=Integer.valueOf(0);
        valueToSend=Float.valueOf(0);
        start();
    }

    public void start() {
        timer.cancel();
        timer = new Timer("TaskName");
        Date executionDate = new Date(); // no params = now
        timer.scheduleAtFixedRate(task, executionDate, delay);
    }

    private class LoopTask extends TimerTask {
        public void run() {
            numberReadingsForAverage+=1;
           valueToSend=Float.valueOf( data.get(currentReadingIndex).get(0));
           currentReadingIndex++;

           if(currentReadingIndex>data.size())
               currentReadingIndex=0;

           String msg=buildMesage();
           if(msg!=null)
           {
               System.out.println("JSON message is"+msg);
               mqSender.sendJson(msg);

           }
           System.out.println("This message will print every 10 minutes.");
        }

    }






    public String buildMesage()
{

    String jsonString=null;
  // if(numberReadingsForAverage > NUMBER_READINGS)
    {
        System.out.println("building message");

        //resenting the hour
        numberReadingsForAverage=Integer.valueOf(0);


        //building time stamp
        LocalDateTime dt= LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        String formatDateTime = dt.format(formatter);
        System.out.println("time of reading is:"+formatDateTime);

        //processing data;
        Float value= valueToSend;

        //reset value of reading
        valueToSend=Float.valueOf(0);

        //build meesage structure
        MessageToSend msg= new MessageToSend();
        msg.setDevice_id(device_id);
        msg.setTimestamp(formatDateTime);
        msg.setMeasurement_value(value);


        //transform to json format
        GsonBuilder gb=new  GsonBuilder();
        gb.setPrettyPrinting();
        Gson gson = gb.create();
        jsonString=gson.toJson(msg);


    }
    return jsonString;

}


}
