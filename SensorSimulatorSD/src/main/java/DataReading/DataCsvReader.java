package DataReading;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataCsvReader {



   final static String COMMA_DELIMITER=",";
   public List<List<String>> readDataCsv() {
        List<List<String>> records = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("sensor.csv"));




                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(COMMA_DELIMITER);
                    records.add(Arrays.asList(values));
                  //  for(int i=0;i<values.length;i++)
                   // System.out.println("values on line "+values[i]);
                }
            }
           catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        return records;
        }

    }
