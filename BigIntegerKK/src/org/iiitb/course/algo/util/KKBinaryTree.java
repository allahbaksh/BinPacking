/**
 * 
 */
package org.iiitb.course.algo.util;



/**
 * @author Allahbaksh_Asadullah
 *
 */
public class KKBinaryTree {
	public Node root; 
	
	public KKBinaryTree(Element[] elementArray){
		root = new Node(elementArray);
	}
	
    public  static class Node { 
	  public  Node left; 
	  public  Node right; 
	  public  Element data[];

	  public  Node(Element[] newData) { 
	      left = null; 
	      right = null; 
	      data = newData; 
	    } 
    }    
     
}
