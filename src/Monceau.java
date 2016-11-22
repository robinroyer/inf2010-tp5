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
    public void delete (int val) throws DifferentOrderTrees, IsNotTreeException {
        
        Node NodeFound;
        ArrayList<Node> arbreTmp;
        ArrayList<Node> arbresTmp;  


        arbresTmp= new ArrayList<>(); 

        for (Node arbre : arbres) {
            arbreTmp = new ArrayList<>();

            NodeFound = arbre.findValue(val);

            if (NodeFound != null) {
               arbreTmp = NodeFound.delete();
               arbresTmp.addAll(arbreTmp);
            }
            else{
                arbresTmp.add(arbre);
            }
        }  
        arbres = arbresTmp;

        while(!isCorrect()){
            clean();
        }
        
        if(contains(val)){
            this.delete(val);
        }
    }
    
    /**
     * display
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
    
    /**
     * check a value in all the trees
     * @param value
     * @return 
     */
    private boolean contains(int value){
        for (Node arbre : arbres) {
            if(arbre.findValue(value) != null){
                return true;
            }
        }
        return false;
    }
    
}
