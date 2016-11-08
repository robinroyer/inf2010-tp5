/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author robin royer
 */
public class IsNotTreeException extends Exception {

    public IsNotTreeException() {
        super("Erreur : Ce node n'est pas un arbre, il a un parent");
    }

    public IsNotTreeException(String s) {
        super(s);
    }
}
