/**
 * Created by justi_000 on 2018-03-01.
 */
public class Pool {
    //public String NAME;
    public Properties properties;
    public Geometry geometry;
    public Double[] coordinates;

    public String toString(){
        return properties.NAME + "  " + "[" + geometry.coordinates[0] + ", "
                + geometry.coordinates[1] + "]";
    }
}
