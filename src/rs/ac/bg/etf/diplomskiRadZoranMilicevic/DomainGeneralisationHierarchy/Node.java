package rs.ac.bg.etf.diplomskiRadZoranMilicevic.DomainGeneralisationHierarchy;


import java.util.ArrayList;

public class Node {
    private String data;
    private Node parent;
    private ArrayList<Node> children;
    private int depth;

    public Node(String data, Node parent, int depth) {
        this.data = data;
        this.parent = parent;
        this.children = new ArrayList<>();
        this.depth = depth;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void addChild(Node child){
        children.add(child);
    }
}
