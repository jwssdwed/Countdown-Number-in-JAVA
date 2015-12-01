package BFS;

import java.util.ArrayList;

public class Search {
	static private String[] operation = {"+","-","*","/"};
	static private ArrayList<Tree> treeStr = new ArrayList<Tree>(); 
	static private int[] numbers = {41, 11, 5, 4, 6, 1};
	static private int random;
	static long startMili = System.currentTimeMillis();
	
	public static long getStartMili(){
		return startMili;
	}
	public static int[] getNumbers() {
		return numbers;
	}
	
	public static ArrayList<Tree> getTreeStr() {
		return treeStr;
	}

	public static String[] getOperation() {
		return operation;
	}
	
	public static int getRandom(){
		return random;
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		
		
		random = (int) (1+Math.random()*999);
		System.out.println("The target number is: " + random);

		System.out.println("Searcing...");
		
		//exp with one number
		System.out.println("one number...");
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i]==random) {
				System.out.println("One correct expression "+numbers[i]);
				System.exit(1);
			}
		}
		
		//two numbers
		System.out.println("two numbers...");
		NOperation one =new NOperation(1);
		one.buildOneTree();
		
		//three numbers
		System.out.println("three numbers...");
		NOperation two = new NOperation(2);
		two.buildTree();
		
		//four
		System.out.println("four numbers...");
		NOperation three = new NOperation(3);
		three.buildTree();

		//five
		System.out.println("five numbers...");
		NOperation fou = new NOperation(4);
		fou.buildTree();
		
		//six
		System.out.println();
		System.out.println("six numbers...");
		NOperation fiv = new NOperation(5);
		fiv.buildTree();
		
		long endMili = System.currentTimeMillis();
		System.out.println("Dear Amin, Total Time: "+ (endMili- Search.getStartMili())/1000);
		
	}
}
