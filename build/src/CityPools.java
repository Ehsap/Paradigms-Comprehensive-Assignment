import com.github.filosganga.geogson.gson.GeometryAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
Justin Huynh
7745112
Comprehensive Assignment
 */

public class CityPools {

    public static void main(String[]args){
        sortPools();
    }

    //Sorts the pools from west to east
    public static void sortPools() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GeometryAdapterFactory())
                .create();
        try {
            Pool[] pools = gson.fromJson(new FileReader("wading-pools-min.json"), Pool[].class);
            //SortbyLongitude sortLong = new SortbyLongitude();
            Arrays.sort(pools);
            for (int i = 0; i < pools.length; i++)
                System.out.println(pools[i]);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
    }
    public static double euclidDistance(double lat1, double lon1, double lat2, double lon2){
       double rLat1 = Math.toRadians(lat1);
       double rLat2 = Math.toRadians(lat2);
       double rLon1 = Math.toRadians(lon1);
       double rLon2 = Math.toRadians(lon2);
       double dRadians;

       dRadians = 2*Math.asin(Math.sqrt(Math.pow((Math.sin((rLat1 - rLat2)/2)),2) + (Math.cos(rLat1) * Math.cos(rLat2) * Math.pow(Math.sin((rLon1 - rLon2)/2),2))));

       return 6371.0 * dRadians;
   }

}
