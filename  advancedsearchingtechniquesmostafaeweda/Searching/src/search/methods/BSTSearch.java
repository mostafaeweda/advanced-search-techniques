package search.methods;

import java.util.Comparator;

import list.AbstractList;

import trees.binary.BST;
import trees.binary.BinaryNode;

public class BSTSearch<E, K extends Comparator<E>> extends AbstractSearch<E, K> {

	private BST<E, K> tree;

	public BSTSearch(AbstractList<E> list, K comp) {
		super(list, comp);
		tree = new BST<E, K>(comp);
		tree.addRoot(new BinaryNode<E>(list.get(0)));
		for (int i = 1, n = list.size(); i < n; i++)
			tree.insert(list.get(i));
	}

	@Override
	public E find(E e) {
		return tree.find(e);
	}

	@Override
	public E[] findAll(E e) {
		tree.findAll(e);
		return null;
	}

}
