package BFS;

import java.util.ArrayList;

public class NOperation {
	int size;
	ArrayList<Tree> expression = new ArrayList<Tree>();
	ArrayList<String[]> numberArray = new ArrayList<String[]>();
	
	public NOperation(int a){
		size = a+1;
		Combination aa=new Combination();
    	numberArray =Combination.select(Search.getNumbers(),size);		//build ArrayNumber
    	aa.clear();
	}
	
	public void buildOneTree() throws CloneNotSupportedException{
		ArrayList<Tree> treeStructure = new ArrayList<Tree>();
		for (int i = 0; i < Search.getOperation().length; i++) {
			Tree tree =new Tree(Search.getOperation()[i]);
			treeStructure.add(tree);
		}
		for (int i = 0; i < Search.getTreeStr().size(); i++) {
			Search.getTreeStr().remove(i);
		}
		for (int i = 0; i < treeStructure.size(); i++) {
			Search.getTreeStr().add(treeStructure.get(i));
		}
		
		for (int i = 0; i < treeStructure.size(); i++) {
			for (int j = 0; j < numberArray.size(); j++) {
				Tree tree = new Tree();
				clone(treeStructure.get(i),tree);
				ArrayList<String> numberList = new ArrayList<String>();
				for (int k = 0; k < size; k++) {
					numberList.add(numberArray.get(j)[k]);	//add the result of permutation and combination
				}
				BoolInt b = addNumber1(tree, numberList);
				if (b.b) {
					if (b.a == Search.getRandom()) {
		        		System.out.print("One correct expression "); 
						System.out.println(Tree.treeToStr(tree, ""));
						long endMili = System.currentTimeMillis();
						System.out.println("Dear Amin, Total Time: "+ (endMili- Search.getStartMili())/1000);	
						System.exit(1);
					}
				}
				
				
			}
		}
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
					numberList.add(numberArray.get(j)[k]);	//add the result of permutation and combination
				}
				
//				addNumber(tree, numberList);
//				String string = Tree.treeToStr(tree,"");
//				calculate(string);				//when generate one valid expression, calculate it immediately.
				
				
				BoolInt b = addNumber1(tree, numberList);
				if (b.b) {
					if (b.a == Search.getRandom()) {
		        		System.out.print("One correct expression ");
		        		
						System.out.println(Tree.treeToStr(tree, ""));
						long endMili = System.currentTimeMillis();
						System.out.println("Dear Amin, Total Time: "+ (endMili- Search.getStartMili())/1000);
						System.out.println("LOL, Beat the Haskell one you provide!");
						System.exit(1);
					}
				}
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
			number.remove(0);
			number.remove(0);
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
	
	public BoolInt addNumber1(Tree tree,  ArrayList<String> number) throws CloneNotSupportedException{
		if(tree.lefTree==null && tree.rigTree==null){
			int c = Integer.parseInt(number.get(0));
			int d = Integer.parseInt(number.get(1));
			BoolInt bI;
			int a;
			if (tree.root.equals("/")) {
				if (d!=0&&(a=(c/d))==0) {
					tree.lefTree = new Tree(number.get(0));
					tree.rigTree = new Tree(number.get(1));
					number.remove(0);
					number.remove(0);
					bI = new BoolInt(true,a);
					return bI;
					
				}
				else {
					bI = new BoolInt(false, 0);
					return bI;
				}
				
			}
			else if (c<d) {
				bI = new BoolInt(false, 0);
				return bI;
			}
			else {
				tree.lefTree = new Tree(number.get(0));
				tree.rigTree = new Tree(number.get(1));
				number.remove(0);
				number.remove(0);
				if (tree.root.equals("+")) {
					bI = new BoolInt(true, c+d);

					return bI;

				}
				if (tree.root.equals("-")) {
					bI = new BoolInt(true, c-d);
					return bI;
				}
				else {
					bI = new BoolInt(true, c*d);
					return bI;
				}
			}
			
		}
		else if (tree.lefTree!=null && tree.rigTree==null) {
			int n = Integer.parseInt(number.get(0));
			tree.rigTree = new Tree(number.get(0));
			number.remove(0);
			BoolInt bI = addNumber1(tree.lefTree, number);
			if (bI.b) {
				int a;
				if (tree.root.equals("/")) {
					if (n!=0&&(a =(bI.a/n))==0) {
						bI = new BoolInt(true,a);
						return bI;
					}
					else {
						bI = new BoolInt(false , 0);
						return bI;
					}
				}
				else if (bI.a<n) {
					bI = new BoolInt(false, 0);
					return bI;
				}
				else {
					if (tree.root.equals("+")) {
						bI = new BoolInt(true, bI.a+n);
						return bI;
					}
					if (tree.root.equals("-")) {
						bI = new BoolInt(true, bI.a-n);
						return bI;
					}
					else {
						bI = new BoolInt(true, bI.a*n);
						return bI;
					}
				}
			}
			else {
				bI = new BoolInt(false, 0);
				return bI;
			}
			
		}
		else if (tree.lefTree==null && tree.rigTree!=null) {
			int n = Integer.parseInt(number.get(0));
			tree.lefTree = new Tree(number.get(0));
			number.remove(0);
			BoolInt bI = addNumber1(tree.rigTree, number);

			if (bI.b) {
				int a;
				if (tree.root.equals("/")) {
					if (bI.a!=0&&(a =(n/bI.a))==0) {
						bI = new BoolInt(true,a);
						return bI;
					}
					else {
						bI = new BoolInt(false, 0);
						return bI;
					}
				}
				else if (n<bI.a) {
					bI = new BoolInt(false, 0);
					return bI;
				}
				else {
					if (tree.root.equals("+")) {
						bI = new BoolInt(true, n+bI.a);
						return bI;
					}
					if (tree.root.equals("-")) {
						bI = new BoolInt(true, n-bI.a);
						return bI;
					}
					else {
						bI = new BoolInt(true, n*bI.a);
						return bI;
					}
				}
			}
			else {
				bI = new BoolInt(false, 0);
				return bI;
			}
		}
		else {
			BoolInt bI1 = addNumber1(tree.lefTree, number);
			BoolInt bI2 = addNumber1(tree.rigTree, number);
			if (bI1.b&&bI2.b) {
					int a;
					if (tree.root.equals("/")) {
						if (bI2.a!=0&&(a =(bI1.a/bI2.a))==0) {
							bI1 = new BoolInt(true,a);
							return bI1;
						}
						else {
							bI1 = new BoolInt(false, 0);
							return bI1;
						}
					}
					else if (bI1.a<bI2.a) {
						bI1 = new BoolInt(false, 0);
						return bI1;
					}
					else {
						if (tree.root.equals("+")) {
							bI1 = new BoolInt(true, bI1.a+bI2.a);
							return bI1;
						}
						if (tree.root.equals("-")) {
							bI1 = new BoolInt(true, bI1.a-bI2.a);
							return bI1;
						}
						else {
							bI1 = new BoolInt(true, bI1.a*bI2.a);

							return bI1;
						}
					}
				
			}
			else {
				bI1 = new BoolInt(false, 0);
				return bI1;
			}
		}
	}
	
	public void printExp() {
		for (int i = 0; i < expression.size(); i++) {
			Tree.inOrder(expression.get(i));
			System.out.println("");
		}
		System.out.println("expression size: "+expression.size());
		
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
