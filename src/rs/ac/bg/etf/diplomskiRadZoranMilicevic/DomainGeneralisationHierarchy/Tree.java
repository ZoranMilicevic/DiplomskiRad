package rs.ac.bg.etf.diplomskiRadZoranMilicevic.DomainGeneralisationHierarchy;

import java.util.ArrayDeque;
import java.util.Queue;

public class Tree {
    private Node root;
    private int generalisationLevels;

    public Tree(Node root) {
        this.root = root;
    }

    public Tree(Node root, int generalisationLevels) {
        this.root = root;
        this.generalisationLevels = generalisationLevels;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getGeneralisationLevels() {
        return generalisationLevels;
    }

    public void setGeneralisationLevels(int generalisationLevels) {
        this.generalisationLevels = generalisationLevels;
    }

    //------------------------------------------------------

    //data - data to search for in tree
    public Node bfsSearch(String data){
        Queue<Node> visited = new ArrayDeque<>();
        Queue<Node> unvisited = new ArrayDeque<>();

        visited.add(root);
        unvisited.add(root);

        while(!unvisited.isEmpty()){
            Node current = unvisited.remove();
            visited.add(current);
            if(current.getData().equals(data)){
                return current;
            }
            else{
                for(Node n: current.getChildren()){
                    if(!visited.contains(n)) unvisited.add(n);
                }
            }
        }

        return null;
    }

    //Inserts a node given its parent.
    //returns true if successful
    //false if failed
    public boolean insert(Node parent, Node child){
        Node n = bfsSearch(parent.getData());
        if(n!=null){
            n.addChild(child);
            return true;
        }
        else return false;
    }

    //returns parent of a node given its data
    public Node getParent(String data){
        Node n = bfsSearch(data);
        if(n!=null)return n.getParent();
        else return null;
    }

    public void printTree(){
        Queue<Node> visited = new ArrayDeque<>();
        Queue<Node> unvisited = new ArrayDeque<>();

        visited.add(root);
        unvisited.add(root);

        while(!unvisited.isEmpty()){
            Node current = unvisited.remove();
            visited.add(current);
            System.out.println(current.getData() + " " + current.getDepth());
            for(Node n: current.getChildren()){
                if(!visited.contains(n)) unvisited.add(n);
            }
        }
    }
}
