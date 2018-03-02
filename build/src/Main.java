import com.github.filosganga.geogson.gson.GeometryAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.*;
import java.io.FileReader;

public class Main {

    /*
    1. Parses the JSON file to only produce JSON objects with NAME
    and coordinates to simplify the file.

    2. Parses JSON file to create pool(
     */

    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GeometryAdapterFactory())
                .create();
        try {
            Pool[] pools = gson.fromJson(new FileReader("wading-pools.json"), Pool[].class);
            String poolsMin = gson.toJson(pools);
            PrintWriter out = new PrintWriter(new FileWriter("wading-pools-min.json"));
            out.print(poolsMin);
            out.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("File not found!");
        }
        catch(IOException ex){
            System.out.println("Could not write to file");
        }

    }

}
