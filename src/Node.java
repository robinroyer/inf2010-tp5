
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maitr
 */
public class Node {

    /**
     * tab char for display purpose
     */
    private static final String TAB = "\t";
    
    /**
     * 
     */
    public int ordre;
    
    /**
     * Reference to the parent node
     */
    public Node parent;

    /**
     * Value hold by the node
     */
    private final int valeur;
    
    /**
     * All the children
     */
    private ArrayList<Node> enfants;

    /**
     * Constructor
     * @param valeur node's value
     */
    public Node(int valeur) {
        this.valeur = valeur;
        ordre = 0;
        this.parent = null;
        enfants = new ArrayList<>();
    }

    /**
     * constuctor
     * @param valeur node's value
     * @param parent reference to the parent
     */
    public Node(int valeur, Node parent) {
        ordre = 0;
        this.valeur = valeur;
        this.parent = parent;
        enfants = new ArrayList<>();
    }

    /**
     * value getter
     * @return int
     */
    public int getVal() {
        return valeur;
    }

    /**
     * enfants getter
     * @return ArrayList of children node
     */
    public ArrayList<Node> getEnfants() {
        return enfants;
    }

    /**
     * Push an enfant to the node
     * @param enfant 
     */
    public void addEnfant(Node enfant) {
        enfants.add(enfant);
    }

    /**
     * Remove an enfant from the node
     * @param enfant enfant to remove
     * @return true if succeed
     */
    public boolean removeEnfant(Node enfant) {
        if (enfants.contains(enfant)) {
            enfants.remove(enfant);
            return true;
        }
        return false;
    }

    /**
     * Empty a list of children's node
     * @param enfants ArrayList of children to remove
     */
//    public void removeEnfants(ArrayList<Node> enfants) {
//        this.enfants.removeAll(enfants);
//    }

    /**
     * Merge 2 nodes together
     * @param autre second node to merge
     * @return the merged node
     * 
     * @throws DifferentOrderTrees execption if node are not the same level
     * @throws IsNotTreeException  exception if this node are not tree
     *          (parent should be null)
     */
    public Node fusion(Node autre) throws DifferentOrderTrees, IsNotTreeException {
        if (ordre != autre.ordre) 
            throw new DifferentOrderTrees();
        
        if(this.parent != null && autre.parent != null)
            throw new IsNotTreeException();
        
        // The tree with the bigger root becomes an enfant of the other tree
        Node newTree;
        if (this.getVal() < autre.getVal()) {
            newTree = this;
            newTree.addEnfant(autre);
            autre.parent = this;
        }
        else{
            newTree = autre;
            newTree.addEnfant(this);
            this.parent = newTree;
        }
        newTree.parent = null;
        newTree.ordre += 1;
        return newTree;
    }

    /**
     * Move up a node if he has a parent
     */
    private void moveUp() {    
        if (this.parent == null)
            return;
        
        // keeping ref to all object
        Node parentPivot = this.parent;
        Node grandParentPivot = this.parent.parent;
        ArrayList<Node> enfantsPivot = this.getEnfants();        
        ArrayList<Node> parentEnfantsPivot = this.parent.getEnfants();
        // /!\ this is in this list => thats why we remove it
        parentEnfantsPivot.remove(this);
        
        // changing grandParent
        if (grandParentPivot != null) {
            grandParentPivot.removeEnfant(parentPivot);
            grandParentPivot.addEnfant(this);
        }
        
        // changing this
        this.enfants = parentEnfantsPivot;
        this.addEnfant(parentPivot);
        this.parent = grandParentPivot;
        
        // changing old parent
        parentPivot.enfants = enfantsPivot; 
        parentPivot.parent = this;
        parentPivot.ordre--;
        
        //changing old parent enfants
        for (Node parentEnfants : parentEnfantsPivot) {
            parentEnfants.parent = this;
        }
        
        //changing old enfants
        for (Node enfant : enfantsPivot) {
            enfant.parent = parentPivot;
        }
    }

    /**
     * Delete this node from the tree by moving it to the top
     * @return ArrayList of children node
     */
    public ArrayList<Node> delete() {
        while (this.parent != null) {            
            this.moveUp();
        }
        
        for (Node enfant : enfants) {
            enfant.parent = null;
        }
        return this.getEnfants();
    }

    /**
     * Display a node to the screen recursively
     * @param tabulation
     * @param firstTab 
     */
    public void print(String tabulation, String firstTab) {
        // print this.value
        System.out.print(firstTab + this.getVal());
        // call print on enfants
        if (enfants.isEmpty()) {
            System.out.println(""); // no more enfants => endline
        }
        else{
            tabulation+= TAB;
            for (int i = 0; i < enfants.size(); i++) { 
                if (i == 0) {
                    enfants.get(i).print(tabulation, TAB);
                }
                else{
                    enfants.get(i).print(tabulation, tabulation);
                }
            }
        }        
    }
    
    /**
     * search in this node and in its children if the value can be found
     * @param valeur value to find
     * @return reference to the node containing the value
     */
    public Node findValue(int valeur) {  
        Node result = null;
        
        if (this.valeur == valeur)
            return this;
        
        for (Node enfant : enfants) {
            if (enfant.getVal() <= valeur) {
                result = enfant.findValue(valeur);
                // => if value found
                if(result != null)
                    return result;
            }
        }
        return result;
    }
}
