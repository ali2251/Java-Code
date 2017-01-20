package apiProcessing;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.*;
import frontend.JsonToCharts;

/**
 * Created by Ali on 03/12/2016.
 */
public class SerialisationMain {

    /**
     *
     * @param jsonProcessor
     * @return
     */
    public static boolean write(JsonProcessor jsonProcessor) {
        try {
            FileOutputStream fileOut = new FileOutputStream("JsonProcessor.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(jsonProcessor);
            out.close();
            fileOut.close();
            return true;
        } catch (Exception i) {
            return false;
        }

    }

    /**
     *Method to read cached data 
     * @return
     */
    public static JsonProcessor read() {
        JsonProcessor jsonProcessor = null;
        try {
            FileInputStream fileIn = new FileInputStream("JsonProcessor.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            jsonProcessor = (JsonProcessor) in.readObject();
            in.close();
            fileIn.close();
        }catch(Exception i) {
            return null;
        }

      return  jsonProcessor;
    }

    /**
     * check to see if it's cached
     * @return return true if it is cached, else return false
     */
    public static boolean isCached() {

        try {
            FileInputStream fileIn = new FileInputStream("JsonProcessor.ser");
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
