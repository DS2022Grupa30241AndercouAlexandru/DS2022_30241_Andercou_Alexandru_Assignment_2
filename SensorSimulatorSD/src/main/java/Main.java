import DataReading.RabbitMQSender;

public class Main {

    public static void main(String[] args) {

        DataProcessor dp;

        if(args.length>0)
        dp=new  DataProcessor(Long.valueOf(args[0]));
        else
        dp=  new  DataProcessor(Long.valueOf(17));
        dp.buildMesage();
    }
}