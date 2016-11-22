import java.util.ArrayList;

public class Monceau {
    public ArrayList<Node> arbres;
    
    /**
     * Default constructor
     */
    public Monceau() {
        arbres = new ArrayList<>();
    }
    
    /**
     * Merge a monceau in this
     * @param autre 
     * @throws DifferentOrderTrees 
     * @throws IsNotTreeException 
     */
    public void fusion(Monceau autre) throws DifferentOrderTrees, IsNotTreeException {
        for (Node arbre : autre.arbres) {
            insert(arbre);
        }
    }
    
    /**
     * insert a value in a monceau
     * @param val
     * @throws DifferentOrderTrees
     * @throws IsNotTreeException   */
    public void insert(int val) throws DifferentOrderTrees, IsNotTreeException {
        Node newNode = new Node(val);
        insert(newNode);
    }
   
    
    /**
     * delete all value from a monceau
     * @param val
     * @return 
     * @throws DifferentOrderTrees 
     * @throws IsNotTreeException 
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
        arbres.add(tree);  
        // ENHANCEMENT: Optimization should be do here 
        // ===>  bad complexity
        while(!isCorrect()){
            clean();
        }
    }
    
    
    /**
     * merge 2 same order trees
     * @return 
     */
    private void clean() throws DifferentOrderTrees, IsNotTreeException{
        for (Node arbre1 : arbres) {
            for (Node arbre2 : arbres) {
                if(arbre1.ordre == arbre2.ordre && arbre1 != arbre2) {
                    arbres.add(arbre1.fusion(arbre2));
                    arbres.remove(arbre1);
                    arbres.remove(arbre2);
                    return;
                }
            }            
        }
    }
    
    /**
     * check that the monceau doesn't have 2 same order trees
     * @return 
     */
    private boolean isCorrect(){
        for (Node arbre1 : arbres) {
            for (Node arbre2 : arbres) {
                if(arbre1.ordre == arbre2.ordre && arbre1 != arbre2)
                    return false;
            }            
        }
        return true;
    }
    
}
