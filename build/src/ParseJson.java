import com.github.filosganga.geogson.gson.GeometryAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.*;
import java.io.FileReader;

    /*
    1. Parses the JSON file to only produce JSON objects with NAME
    and coordinates to simplify the file.

    2. Parses JSON file to create a prolog database of pools
     */

public class ParseJson {

    public static void main(String[] args) {
        //createMinJSON(); Uncomment this to recreate the minimized JSON file.
        createMinPL();
    }

    public static void createMinJSON(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GeometryAdapterFactory())
                .create();
        try {
            Pool[] pools = gson.fromJson(new FileReader("wading-pools.json"), Pool[].class);
            String poolsMin = gson.toJson(pools);

            PrintWriter out = new PrintWriter(new FileWriter("wading-pools-min.json"));
            out.print(poolsMin); //This prints the minimized JSON file of pools with only NAME and COORDINATES
            out.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("File not found!");
        }
        catch(IOException ex){
            System.out.println("Could not write to file");
        }
    }

    //Parses the wading-pools-min.JSON file and creates a prolog database off the parsed data
    public static void createMinPL(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GeometryAdapterFactory())
                .create();
        try {
            Pool[] pools = gson.fromJson(new FileReader("wading-pools-min.json"), Pool[].class);
            PrintWriter out = new PrintWriter(new FileWriter("pools.pl"));
            for (int i = 0; i < pools.length; i++)
                out.println("pool(" + pools[i] + ").");
            out.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("File not found!");
        }
        catch(IOException ex){
            System.out.println("Could not write to file!");
        }
    }

}
