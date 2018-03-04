/**
 * Created by justi_000 on 2018-03-01.
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Pool implements Comparable<Pool> {
    //public String NAME;
    public Properties properties;
    public Geometry geometry;
    //public Double[] coordinates;

    public int compareTo(Pool other){
        return this.geometry.coordinates[0].compareTo(other.geometry.coordinates[0]);
    }
    public String toString(){
        return   properties.NAME  + "[" + geometry.coordinates[0] + ", "
                + geometry.coordinates[1] + "]";
    }
}
