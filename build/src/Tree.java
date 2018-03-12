import java.util.ArrayList;
import java.util.List;

/**
 * Created by justi_000 on 2018-03-03.
 */
public class Tree{
    public Node root;
    List<Pool> route = new ArrayList<Pool>();//List of pools to visit from 1st to last.
    public Tree(){}


    public void addRoot(Pool pool){
        root = new Node(pool);
        root.parent = null;
        root.children = new ArrayList<Node>();
    }

    //Adds node as child of the closest pool
    public void addNode(Node u, Pool pool, Double distance){
        Node child = new Node(pool);
        child.distanceFromParent = distance;
        u.addChild(child);
    }

    //Preorder traversal through each node in the tree
    public void preOrder(Node root){
        if (root != null){
            //Store the route in an array, route0] = first stop, route[1] = 2nd stop...etc
            System.out.println("Added " + root.info);
            route.add(root.info);

            for (Node i : root.children){
                preOrder(root.children.get(root.children.indexOf(i)));
            }
        }
    }

    //Prints the pre-order route
    public void printRoute(){
        Double totalDistance = 0.0;
        System.out.println(route.get(0).properties.NAME + " " + totalDistance); //Root pool, distance is 0 by default
        for (int i = 1; i< route.size(); i++){
            System.out.println(route.get(i).properties.NAME + " " +
                    (totalDistance += euclidDistance(route.get(i-1).geometry.coordinates[1],
                                                route.get(i-1).geometry.coordinates[0],
                                                route.get(i).geometry.coordinates[1],
                                                route.get(i).geometry.coordinates[0])));
        }
    }

    //Add an edge between an existing pool in the tree and a new pool
    public void addEdge(Node root, Pool closestPool, Pool newPool, Double distance) {
        if (root != null) {
            if (root.info.equals(closestPool)) {
                //System.out.println("EDGE created from " + root.info + "  to  " + newPool);
                this.addNode(root, newPool, distance);
            }
            for (Node i : root.children) {
                addEdge(root.children.get(root.children.indexOf(i)), closestPool, newPool, distance);
            }
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