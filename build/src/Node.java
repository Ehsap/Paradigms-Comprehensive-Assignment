import java.util.ArrayList;
import java.util.List;

/**
 * Created by justi_000 on 2018-03-03.
 */

public class Node{
    Node parent; //Parent of the current node
    Pool info; //Info about the current node
    List<Node> children; //Children of current node
    Double distanceFromParent = 0.0;

    public Node(Pool info){
        this.info = info;
        children = new ArrayList<Node>();
    }

    public void addChild(Node childNode){
        childNode.parent = this;
        //this.children.set(position,childNode);
        this.children.add(childNode);
    }
}
