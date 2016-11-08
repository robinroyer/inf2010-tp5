
import java.util.ArrayList;

public class Monceau {
    ArrayList<Node> arbres;
    
    /**
     * Default constructor
     */
    public Monceau() {
        arbres = new ArrayList<Node>();
    }
    
    /**
     * merge a monceau in this
     * @param autre 
     */
    public void fusion(Monceau autre) throws DifferentOrderTrees, IsNotTreeException {
        for (Node arbre : autre.arbres) {
            insert(arbre);
        }
    }
    
    /**
     * insert a value in a monceau
     * @param val 
     */
    public void insert(int val) throws DifferentOrderTrees, IsNotTreeException {
        Node newNode = new Node(val);
        insert(newNode);
    }
   
    
    /**
     * delete all value from a monceau
     * @param val
     * @return 
     */
    public boolean delete (int val) throws DifferentOrderTrees, IsNotTreeException {
        boolean hadDeleteSomethig = false;
        Node NodeFound;
        
        for (Node arbre : arbres) {
            NodeFound = null;
            
            // Delete all val from arbre
            NodeFound = arbre.findValue(val);
            // delete tree and merge back tree
            if (NodeFound != null) {
                //remove tree for monceau trees
                arbres.remove(arbre);
                while (NodeFound != null) {
                    // insert back tree 
                    for (Node tree :NodeFound.delete()){
                        insert(tree);
                    }
                    NodeFound = arbre.findValue(val);
                }
            }
            
        }        
        return hadDeleteSomethig;
    }
    
    /**
     * 
     */
    public void print() {
        for (Node arbre : arbres) {
            System.out.println(" ====== Arbre D'ordre :" + arbre.ordre);
            arbre.print("", "");
        }
    }
    
    /**
     * 
     * @param tree
     * @throws DifferentOrderTrees
     * @throws IsNotTreeException 
     */
    private void insert(Node tree) throws DifferentOrderTrees, IsNotTreeException {        
        for (Node arbre : arbres) {
            if (arbre.ordre == tree.ordre) {
                arbres.remove(arbre);
                arbres.add(arbre.fusion(tree));
            }            
            else{
                arbres.add(tree);
            }
        }
    }
}
