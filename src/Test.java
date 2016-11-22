/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author robinroyer
 */
public class Test {
    public static void main(String[] args) throws DifferentOrderTrees, IsNotTreeException {
        Monceau monceau1 = new Monceau();
        Monceau monceau2 = new Monceau();
        
        monceau1.insert(1);        
        monceau1.insert(2);
        monceau1.insert(3);
        monceau1.insert(4);
        monceau1.insert(5);
        monceau1.insert(6);
        monceau1.insert(7);
        monceau1.insert(8);
        monceau1.insert(9);
        monceau1.insert(-1);        
        monceau1.insert(-1);
        monceau1.insert(-2);
        monceau1.insert(-3);
        monceau1.insert(-4);
        monceau1.insert(-5);
        monceau1.insert(-6);
        monceau1.insert(-7);
        monceau1.insert(-8);
        monceau1.insert(-9);
                       
        monceau2.fusion(monceau1);
        
        System.out.println("the 2 monceau should print the same thing ....");
        
        monceau1.print();
        monceau2.print();
        
        monceau1.delete(-1);
        monceau1.delete(-2);
        monceau1.delete(-3);
        monceau1.delete(-4);
        monceau1.delete(-5);
        monceau1.delete(-6);
        monceau1.delete(-7);
        monceau1.delete(-8);
        monceau1.delete(-9);
        System.out.println("Suppression des .... nombre negatifs");
       
        monceau1.print();
    }
}
