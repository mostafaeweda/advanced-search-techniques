package search.methods;

import java.util.Comparator;

import list.AbstractList;

/**
 * Abstract Class holds the common characteristics of the search methods and
 * also provides an abstraction for the methods to be used as one abstract super class
 * 
 * @author Mostafa Mahmoud Mahmoud Eweda
 * @version 1.0
 * @since JDK 1.6
 * 
 * @see AbstractList
 *
 * @param <E> the element to search for
 * @param <K> the applicable comparator of the list
 */
public abstract class AbstractSearch<E, K extends Comparator<E>> {

	/**
	 * The list containing the data for the search
	 */
	protected AbstractList<E> list;

	/**
	 * The comparator used in the search
	 */
	protected K comp;

	/**
	 * number of comparisons done in the last search
	 */
	protected int comparisons;

	/**
	 * the running time of the last search process
	 */
	protected long runningTime;

	public AbstractSearch(AbstractList<E> list, K comp) {
		this.list = list;
		this.comp = comp;
	}

	/**
	 * Searches for the element e contained in the list by different
	 * methodologies implemented in subclasses
	 * @param e
	 * @return the element found; or null if not found
	 */
	public abstract E find(E e);

	/**
	 * Searches for all the elements contained in the list by different
	 * methodologies implemented in subclasses
	 * @param e
	 * @return the elements found; or empty array if none is found
	 */
	public abstract Object[] findAll(E e);

	/**
	 * @return comparisons done in the last search process
	 */
	public int getComparisons() {
		return comparisons;
	}

	/**
	 * @return the running time in nano seconds elapsed in the last search process
	 */
	public long runningTime() {
		return runningTime;
	}
}
