package BFS;

import java.util.ArrayList;

public class Tree implements Cloneable{
	String root = null;
	Tree lefTree, rigTree;
	
	public Tree() {
		
	}
	
	public Tree(String root){
		this.root = root;
	}
	
	public void buildTree(ArrayList<String> ope,Tree tree) throws CloneNotSupportedException {
		
		if (ope.size()==0) {
			return;
		}
		Tree tree1 = (Tree) tree.clone();	
		Tree tree2 = (Tree) tree.clone();
		tree1.root=ope.get(0);
		tree2.root=ope.get(0);
		ope.remove(0);
		buildTree(ope, tree1.lefTree);
		buildTree(ope, tree2.rigTree);
	}
	
	//read sequence: inOrder
	public static void inOrder(Tree currentNode) {   
        if (currentNode != null && currentNode.lefTree != null && currentNode.rigTree != null) {
        	System.out.print("(");
            inOrder(currentNode.lefTree);   
            System.out.print(currentNode.root + "");   
            inOrder(currentNode.rigTree);
            System.out.print(")");
        }
        else {
			System.out.print(currentNode.root);
		}
    }
	
	//read whole tree
	public static String treeToStr(Tree currentNode, String tree) {
		if (currentNode != null && currentNode.lefTree != null && currentNode.rigTree != null) {
			if (currentNode.root.equals("+")) {
				return treeToStr(currentNode.lefTree,tree)+currentNode.root+treeToStr(currentNode.rigTree,tree);
			}
			else if (currentNode.root.equals("-")) {
				if (currentNode.rigTree.root.equals("-")||currentNode.rigTree.root.equals("+")) {
					return treeToStr(currentNode.lefTree, tree)+currentNode.root+"("+treeToStr(currentNode.rigTree, tree)+")";
				}
				else {
					return treeToStr(currentNode.lefTree,tree)+currentNode.root+treeToStr(currentNode.rigTree,tree);
				}
			}
			else if (currentNode.root.equals("*")) {
				if (currentNode.lefTree.root.equals("+")||currentNode.lefTree.root.equals("-")) {
					if (currentNode.rigTree.root.equals("+")||currentNode.rigTree.root.equals("-")) {
						return "("+treeToStr(currentNode.lefTree,tree)+")"+currentNode.root+"("+treeToStr(currentNode.rigTree,tree)+")";
					}
					else{
						return "("+treeToStr(currentNode.lefTree,tree)+")"+currentNode.root+treeToStr(currentNode.rigTree,tree);
					}
				}
				else {
					if (currentNode.rigTree.root.equals("+")||currentNode.rigTree.root.equals("-")) {
						return treeToStr(currentNode.lefTree,tree)+currentNode.root+"("+treeToStr(currentNode.rigTree,tree)+")";
					}
					else{
						return treeToStr(currentNode.lefTree,tree)+currentNode.root+treeToStr(currentNode.rigTree,tree);
					}
				}
			}
			else {
				if (currentNode.lefTree.root.equals("+")||currentNode.lefTree.root.equals("-")) {
					if (currentNode.rigTree.root.equals("+")||currentNode.rigTree.root.equals("-")||currentNode.rigTree.root.equals("*")||currentNode.rigTree.root.equals("/")) {
						return "("+treeToStr(currentNode.lefTree,tree)+")"+currentNode.root+"("+treeToStr(currentNode.rigTree,tree)+")";
					}
					else{
						return "("+treeToStr(currentNode.lefTree,tree)+")"+currentNode.root+treeToStr(currentNode.rigTree,tree);
					}
				}
				else {
					if (currentNode.rigTree.root.equals("+")||currentNode.rigTree.root.equals("-")||currentNode.rigTree.root.equals("*")||currentNode.rigTree.root.equals("/")) {
						return treeToStr(currentNode.lefTree,tree)+currentNode.root+"("+treeToStr(currentNode.rigTree,tree)+")";
					}
					else{
						return treeToStr(currentNode.lefTree,tree)+currentNode.root+treeToStr(currentNode.rigTree,tree);
					}
				}
			}
        }
        else {
			return currentNode.root;
		}
	}
	
	//read tree just contains operation
	static public String ReadInOrder(Tree currentNode){
		if (currentNode == null){
			return "";
		}
		else {
			return "(" +ReadInOrder(currentNode.lefTree) + currentNode.root + ReadInOrder(currentNode.rigTree) + ")";
		}
	}
	
	
	
}
