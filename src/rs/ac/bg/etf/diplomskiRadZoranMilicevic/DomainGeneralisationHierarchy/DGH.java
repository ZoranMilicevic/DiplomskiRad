package rs.ac.bg.etf.diplomskiRadZoranMilicevic.DomainGeneralisationHierarchy;

import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data.GeneralisationTable;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Tree.Node;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Tree.Tree;

import java.util.ArrayList;

public class DGH {
    private Tree tree;

    public DGH(Tree tree) {
        this.tree = tree;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    //-------------------------------------------

    public String generalise(String data){
        Node n = tree.bfsSearch(data);
        if(n.getParent()!=null)return n.getParent().getData();
        else return null;
    }

    //---------------------------------------------

    public static DGH generateDGH(GeneralisationTable genTable){
        Tree tree = null;
        for(ArrayList<String> ars: genTable.getGenTable()){
            for(int i = ars.size()-1; i>-1; i--){
                String s = ars.get(i);
                if(tree==null) {
                    Node root = new Node(s, null,ars.size() - i - 1);
                    tree = new Tree(root, ars.size());
                }
                else if(tree.bfsSearch(s) == null){
                    Node parrent = tree.bfsSearch(ars.get(i+1));
                    Node node = new Node(s, parrent, ars.size()- i - 1);
                    tree.insert(parrent, node);
                }
            }
        }

        if(tree!=null)return new DGH(tree);
        else return null;
    }

}
