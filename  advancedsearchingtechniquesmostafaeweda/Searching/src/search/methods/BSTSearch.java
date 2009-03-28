package search.methods;

import java.util.Comparator;

import list.AbstractList;
import trees.binary.BST;

public class BSTSearch<E, K extends Comparator<E>> extends AbstractSearch<E, K> {

	private BST<E, K> tree;

	public BSTSearch(AbstractList<E> list, K comp) {
		super(list, comp);
		tree = new BST<E, K>(comp);
		for (int i = 0, n = list.size(); i < n; i++) {
			tree.insert(list.get(i));
		}
	}

	@Override
	public E find(E e) {
		long start = System.nanoTime();
		E found = tree.find(e);
		runningTime = System.nanoTime() - start;
		comparisons = tree.getComparsons();
		return found;
	}

	@Override
	public Object[] findAll(E e) {
		return tree.findAll(e);
	}

}
