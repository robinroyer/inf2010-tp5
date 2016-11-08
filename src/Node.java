
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

    private static final String TAB = "\t";
    
    public int ordre;
    public Node parent;

    private int valeur;
    private ArrayList<Node> enfants;

    public Node(int valeur) {
        this.valeur = valeur;
        ordre = 0;
        this.parent = null;
        enfants = new ArrayList<Node>();
    }

    public Node(int valeur, Node parent) {
        ordre = 0;
        this.valeur = valeur;
        this.parent = parent;
        enfants = new ArrayList<Node>();
    }

    public int getVal() {
        return valeur;
    }

    public ArrayList<Node> getEnfants() {
        return enfants;
    }

    public void addEnfant(Node enfant) {
        enfants.add(enfant);
    }

    public boolean removeEnfant(Node enfant) {
        if (enfants.contains(enfant)) {
            enfants.remove(enfant);
            return true;
        }
        return false;
    }

    public void removeEnfants(ArrayList<Node> enfants) {
        this.enfants.removeAll(enfants);
    }

    public Node fusion(Node autre) throws DifferentOrderTrees {
        if (ordre != autre.ordre) {
            throw new DifferentOrderTrees();
        }
        // à compléter
        return null;
    }

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
        
        //changing old parent enfants
        for (Node parentEnfants : parentEnfantsPivot) {
            parentEnfants.parent = this;
        }
        
        //changing old enfants
        for (Node enfant : enfantsPivot) {
            enfant.parent = parentPivot;
        }
        
    }

    public ArrayList<Node> delete() {
        while (this.parent != null) {            
            this.moveUp();
        }
        return this.getEnfants();
    }

    public void print(String tabulation, String firstTab) {
        // print this.value
        System.out.print(firstTab + this.getVal());
        // call print on enfants
        tabulation+= TAB;
        if (enfants == null) {
            System.out.println(""); // no more enfants => endline
        }
        else{
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
    
    public Node findValue(int valeur) {  
        Node result = null;
        
        if (this.valeur == valeur)
            return this;
        
        for (Node enfant : enfants) {
            if (enfant.getVal() < valeur) {
                result = enfant.findValue(valeur);
                // => if value found
                if(result != null)
                    break;
            }
        }        
        return result;
    }
}
