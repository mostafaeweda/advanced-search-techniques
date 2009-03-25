package controller;

import trees.avl.AVLTree;
import trees.binary.BST;
import compartors.IntegerCompartor;

public class MainController {

	public static void main(String[] args) {
		BST<Integer, IntegerCompartor> bTree = new BST<Integer, IntegerCompartor>(new IntegerCompartor());
		bTree.insert(1);
		bTree.insert(2);
		bTree.delete(2);
		System.out.println(bTree);
		BST<Integer, IntegerCompartor> avl = new AVLTree<Integer, IntegerCompartor>(new IntegerCompartor());
		avl.insert(5);
		avl.insert(4);
		avl.insert(3);
		avl.insert(1);
		avl.insert(2);
		avl.delete(2);
		System.out.println(avl);
//		BST<Integer, > bTree = new BST<String>("Baba");
//		bTree.add("Mama");
//		bTree.add("Ahmed");
//		bTree.add("Ahmed");
//		System.out.println(bTree);
	}
}
