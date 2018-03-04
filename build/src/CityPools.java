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
            Arrays.sort(pools);
            //Find the closest pool to Glen Cairn
            Pool closestPool ;
            Double d = 9999.9;
            Double x;
            for (int i = 0; i < pools.length && i != 10; i++){
                x = euclidDistance(pools[i].geometry.coordinates[1],pools[i].geometry.coordinates[0],
                        pools[10].geometry.coordinates[1],pools[10].geometry.coordinates[0]);
                if (x<d) {
                    d = x;
                    closestPool = pools[i];
                    System.out.println(closestPool);
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
    }

    public class Node{
        Node parent; //Parent of the current node
        Pool info; //Info about the current node
        List<Node> children; //Children of current node

        public Node(Pool info){
            this.info = info;
            children = new ArrayList<Node>(0);
        }

        public void addChild(Node childNode, int position){
            childNode.parent = this;
            this.children.set(position,childNode);
        }
    }

    public class Tree{
        public Node root;
        public Tree(){}

        public void addRoot(Pool pool){
            root = new Node(pool);
            root.parent = null;
            root.children = new ArrayList<Node>(0);
        }

        //Adds node as child of the closest pool
        public void addNode(Node u, Pool pool, int i){
            Node child = new Node(pool);
            u.addChild(child, i);
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
