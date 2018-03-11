import java.util.ArrayList;

/**
 * Created by justi_000 on 2018-03-03.
 */
public class Tree{
    public Node root;
    public Tree(Node first){}

    public void addRoot(Pool pool){
        root = new Node(pool);
        root.parent = null;
        root.children = new ArrayList<Node>();
    }

    //Adds node as child of the closest pool
    public void addNode(Node u, Pool pool){
        Node child = new Node(pool);
        u.addChild(child);
    }

    //Preorder traversal through each node in the tree
    public void preOrder(Node root){
        if (root != null){
            System.out.println(root.info);
            preOrder(root.children.get(root.index++));
        }
    }

}