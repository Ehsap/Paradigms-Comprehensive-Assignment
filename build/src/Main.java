import com.github.filosganga.geogson.gson.GeometryAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.*;
import java.io.FileReader;

public class Main {

    /*
    1. Parse the JSON file and store each pool into a tree
    2. Store the most Western pool as the root
    3. Connect the closest pool with an edge as the child of the root
     */

    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GeometryAdapterFactory())
                .create();

        //String json = "{\"type\":\"Point\",\"coordinates\": [23.5,20.125]}";
        try {
            Pool[] pools = gson.fromJson(new FileReader("wading-pools.json"), Pool[].class);
            int j = 0;
            for (int i = 0; i < pools.length; i++) {
                System.out.println(pools[i]);
                j++;
            }
            System.out.println(j);
        }
        catch(FileNotFoundException ex){
            System.out.println("File not found!");
        }

        /*try {
            Pool pool = gson.fromJson(new FileReader("test.json"), Pool.class);
            System.out.println(pool);
            //gson.toJson(pool,System.out);
        }
        catch (FileNotFoundException ex){
            System.out.println("wading-pools.json not found!");
        }*/

    }

}
