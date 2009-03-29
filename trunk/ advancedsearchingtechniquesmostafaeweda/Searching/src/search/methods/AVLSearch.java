package search.methods;

import java.util.Comparator;

import list.AbstractList;
import trees.avl.AVLTree;


/**
 * Class responsible for the construction of the AVL Tree equivalent for the list and
 * manages the search through the tree
 * 
 * @author Mostafa Mahmoud Mahmoud Eweda
 * @version 1.0
 * @since JDK 1.6
 * 
 * @see AbstractSearch
 * @see AbstractList
 *
 * @param <E> the element to search for
 * @param <K> the applicable comparator of the list
 */
public class AVLSearch<E, K extends Comparator<E>> extends AbstractSearch<E, K> {

	private AVLTree<E, K> tree;

	public AVLSearch(AbstractList<E> list, K comp) {
		super(list, comp);
		tree = new AVLTree<E, K>(comp);
		for (int i = 0, n = list.size(); i < n; i++)
			tree.insert(list.get(i));
	}

	@Override
	public E find(E e) {
		long start = System.nanoTime();
		E result = tree.find(e);
		comparisons = tree.getComparsons();
		runningTime = System.nanoTime() - start;
		return result;
	}

	@Override
	public Object[] findAll(E e) {
		return tree.findAll(e);
	}

}
