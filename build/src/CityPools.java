import com.github.filosganga.geogson.gson.GeometryAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
Justin Huynh
7745112
Comprehensive Assignment
 */

public class CityPools {

    //public static void main(String[]args){
       // sortPools();
    //}

    //Sorts the pools from west to east
    public void sortPools() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GeometryAdapterFactory())
                .create();
        try {
            Pool[] pools = gson.fromJson(new FileReader("wading-pools-min.json"), Pool[].class);
            Arrays.sort(pools); //Sorted from west to east
            Tree tree = new Tree();
            tree.addRoot(pools[0]); //Add the most western pool as the root
            tree.addNode(tree.root,pools[1]); //Add the closest pool to the root as the first child

            //Add each pool from west to east into the tree, where edges are made between the closest pools
            Pool closestPool = pools[0];
            Double closestDistance = 9999.9;
            int poolToAdd = 0; //Index of the next pool to add
            int mostRecentPool = 1; //Index of the most recently added pool to the tree
            Double currentDistance;

            //Longitude = coordinates[0] Latitude = coordinates[1]
            for (int i = 2; i < pools.length; i++){
                for (int j = 0; j < mostRecentPool; j++){
                    currentDistance = euclidDistance(pools[i].geometry.coordinates[1],
                                      pools[i].geometry.coordinates[0], pools[j].geometry.coordinates[1],
                                      pools[j].geometry.coordinates[0]);

                    if(currentDistance < closestDistance)
                    {
                        closestDistance = currentDistance;
                        closestPool = pools[j];
                    }
                }
                closestDistance = 9999.9;
                mostRecentPool++;
                tree.addEdge(tree.root,closestPool,pools[i]);
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
    }


    public double euclidDistance(double lat1, double lon1, double lat2, double lon2){
       double rLat1 = Math.toRadians(lat1);
       double rLat2 = Math.toRadians(lat2);
       double rLon1 = Math.toRadians(lon1);
       double rLon2 = Math.toRadians(lon2);
       double dRadians;

       dRadians = 2*Math.asin(Math.sqrt(Math.pow((Math.sin((rLat1 - rLat2)/2)),2) + (Math.cos(rLat1) * Math.cos(rLat2) * Math.pow(Math.sin((rLon1 - rLon2)/2),2))));

       return 6371.0 * dRadians;
   }

}
