//when the number of operations is larger than or equal to5 
package BFS;

import java.util.ArrayList;

public class No2 {
	int size;
	ArrayList<Tree> expression = new ArrayList<Tree>();
	ArrayList<String[]> numberArray = new ArrayList<String[]>();
	
	public No2(int a){
		size = a+1;
		Combination aa=new Combination();
    	numberArray =Combination.select(Search.getNumbers(),size);		//build ArrayNumber
    	aa.clear();
	}

	
	public void buildTree() throws CloneNotSupportedException{
		ArrayList<Tree> treeStructure = new ArrayList<Tree>();
		for (int i = 0; i < Search.getTreeStr().size(); i++) {
			for (int j = 0; j < Search.getOperation().length; j++) {
				Tree tree = new Tree();
				clone(Search.getTreeStr().get(i),tree);
				addNode(Search.getOperation()[j], tree, treeStructure);
			}
			
		}

		
		for (int i = 0; i < treeStructure.size(); i++) {
			for (int j = 0; j < numberArray.size(); j++) {
				Tree tree = new Tree();
				clone(treeStructure.get(i),tree);
				ArrayList<String> numberList = new ArrayList<String>();
				for (int k = 0; k < size; k++) {
					numberList.add(numberArray.get(j)[k]);
				}
				addNumber(tree, numberList);
				expression.add(tree);
			}
		}

		int length = Search.getTreeStr().size();
		
		for (int i = 0; i < length; i++) {
			Search.getTreeStr().remove(0);
		}
		
		for (int i = 0; i < treeStructure.size(); i++) {
			Search.getTreeStr().add(treeStructure.get(i));
		}
		
	}
	
	public void addNode(String o,Tree tree, ArrayList<Tree> treeStructure) throws CloneNotSupportedException{
		Tree treeDup1 = new Tree();
		clone(tree,treeDup1);
		ArrayList<Tree> subTree = new ArrayList<Tree>();
//		OneOpe.inOrder(treeDup1);
		addSub(o, treeDup1, subTree);
		for (int i = 0; i < subTree.size(); i++) {
			treeStructure.add(subTree.get(i));
		}

		
	}
	
	public void addSub(String o,Tree tree, ArrayList<Tree> subTree) throws CloneNotSupportedException{
		if(tree.lefTree==null && tree.rigTree==null){
			Tree treeDup1 = new Tree();
			clone(tree,treeDup1);
			Tree treeDup2 = new Tree();
			clone(tree,treeDup2);
			treeDup1.lefTree = new Tree(o);
			subTree.add(treeDup1);
			treeDup2.rigTree = new Tree(o);
			subTree.add(treeDup2);
		}
		else if (tree.lefTree!=null && tree.rigTree==null) {
			Tree treeDup1 = new Tree();
			clone(tree,treeDup1);
			Tree treeDup2 = new Tree();
			clone(tree,treeDup2);
			treeDup1.rigTree = new Tree(o);
			subTree.add(treeDup1);
			ArrayList<Tree> subTree1 = new ArrayList<Tree>();
			addSub(o, treeDup2.lefTree, subTree1);
			for (int i = 0; i < subTree1.size(); i++) {
				Tree treeDup3 = new Tree();
				clone(treeDup2, treeDup3);
				treeDup3.lefTree = subTree1.get(i);
				subTree.add(treeDup3);
			}
		}
		else if (tree.lefTree==null && tree.rigTree!=null) {
			Tree treeDup1 = new Tree();
			Tree treeDup2 = new Tree();
			clone(tree,treeDup2);
			clone(tree,treeDup1);
			treeDup1.lefTree = new Tree(o);
			subTree.add(treeDup1);
			ArrayList<Tree> subTree1 = new ArrayList<Tree>();
			addSub(o, treeDup2.rigTree, subTree1);
			for (int i = 0; i < subTree1.size(); i++) {
				Tree treeDup3 = new Tree();
				clone(treeDup2, treeDup3);
				treeDup3.rigTree = subTree1.get(i);
				subTree.add(treeDup3);
			}
		}
		else {
			Tree treeDup1 = new Tree();
			Tree treeDup2 = new Tree();
			clone(tree,treeDup2);
			clone(tree,treeDup1);
			ArrayList<Tree> subTree1 = new ArrayList<Tree>();
			addSub(o, treeDup1.lefTree, subTree1);
			for (int i = 0; i < subTree1.size(); i++) {
				Tree treeDup3 = new Tree();
				clone(treeDup1, treeDup3);
				treeDup3.lefTree = subTree1.get(i);
				subTree.add(treeDup3);
			}
			ArrayList<Tree> subTree2 = new ArrayList<Tree>();
			addSub(o, treeDup1.lefTree, subTree1);
			for (int i = 0; i < subTree2.size(); i++) {
				Tree treeDup3 = new Tree();
				clone(treeDup2, treeDup3);
				treeDup2.rigTree = subTree2.get(i);
				subTree.add(treeDup2);
			}
		}
	}
	
	public void addNumber(Tree tree,  ArrayList<String> number) throws CloneNotSupportedException{
		
		if(tree.lefTree==null && tree.rigTree==null){
			tree.lefTree = new Tree(number.get(0));
			tree.rigTree = new Tree(number.get(1));
		}
		else if (tree.lefTree!=null && tree.rigTree==null) {
			tree.rigTree = new Tree(number.get(0));
			number.remove(0);
			addNumber(tree.lefTree, number);
		}
		else if (tree.lefTree==null && tree.rigTree!=null) {
			tree.lefTree = new Tree(number.get(0));
			number.remove(0);
			addNumber(tree.rigTree, number);
		}
		else {
			addNumber(tree.lefTree, number);
			addNumber(tree.rigTree, number);
		}
	}
	
	public void printExp() {
		for (int i = 0; i < expression.size(); i++) {
			Tree.inOrder(expression.get(i));
			System.out.println("");
		}
		System.out.println(expression.size());
		
	}
	
	public void clone(Tree tree, Tree treeCop) {
		
		treeCop.root = tree.root;
		if (tree.lefTree!=null) {
			treeCop.lefTree = new Tree();
			clone(tree.lefTree, treeCop.lefTree);
		}
		if (tree.rigTree!=null) {
			treeCop.rigTree = new Tree();
			clone(tree.rigTree, treeCop.rigTree);
		}
	}
}
