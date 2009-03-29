package controller;

import java.util.Random;

import list.AbstractList;
import list.UnsortedList;
import search.compartors.IntegerCompartor;
import search.methods.AVLSearch;
import search.methods.AbstractSearch;
import search.methods.BSTSearch;
import search.methods.BinarySearch;
import search.methods.InterpolationSearch;
import search.methods.SequentialSearch;

/**
 * Main controller: Class that handles dealing with the details of the searching system
 * without the interference of the UI in this process;
 * 
 * @author Mostafa Mahmoud Mahmoud Eweda
 * @version 1.0
 * @since JDK 1.6
 * 
 * @see AbstractList
 *
 */
public class MainController {

	private AbstractList<Integer> manualList;
	private AbstractList<Integer> sortedList;
	private AbstractList<Integer> unsortedList;

	/**
	 * Previous search operation cost in running time in nano seconds
	 */
	private long runningTime;

	/**
	 * Previous search operation cost in comparisons done
	 */
	private int comparisons;

	/** 
	 * @param size the size of the list
	 * @return a randomly generated sorted list
	 */
	public AbstractList<Integer> generateSortedList(int size) {
		AbstractList<Integer> list = new UnsortedList<Integer>(size);
		Random random = new Random();
		list.add(Math.abs(random.nextInt()) % 100);
		for (int i = 1; i < size; i++)
			list.add(list.get(i - 1) + Math.abs(random.nextInt() % 20) + 1);
		return sortedList = list;
	}

	/** 
	 * @param size the size of the list
	 * @return a randomly generated unsorted list
	 */
	public AbstractList<Integer> generateUnsortedList(int size) {
		AbstractList<Integer> list = new UnsortedList<Integer>(size);
		Random random = new Random();
		for (int i = 0; i < size; i++)
			list.add(Math.abs(random.nextInt() % ( 20 * size)) + 1);
		return unsortedList = list;
	}

	/**
	 * @param listType
	 * @return the list of the users choice given its type name
	 */
	private AbstractList<Integer> listGetter(String listType) {
		if (listType.equals("Unsorted")) {
			return unsortedList;
		}
		else if (listType.equals("Sorted")) {
			return sortedList;
		}
		else if (listType.equals("Manual"))
			return manualList;
		else
			throw new IllegalArgumentException("list type isn't included");
	}

	/**
	 * searches the given list given its type
	 * @param listType the type of the list {manual, sorted, unsorted}
	 * @param key the element key to find
	 * @return the result of the search
	 */
	public Integer sequentialSearch(String listType, int key) {
		AbstractList<Integer> list = listGetter(listType);
		AbstractSearch<Integer, IntegerCompartor> search =
			new SequentialSearch<Integer, IntegerCompartor>(list,
					new IntegerCompartor());
		Integer result = search.find(key);
		runningTime = search.runningTime();
		comparisons = search.getComparisons();
		return result ;
	}

	/** 
	 * @param size the size of the list
	 * @return an empty unsorted list of the given size
	 */
	public void generateManualList(int size) {
		manualList = new UnsortedList<Integer>(size);
	}

	/**
	 * adds a value to the manual list available to the user for editing
	 * @param value
	 */
	public void addManual(int value) {
		manualList.add(value);
	}

	/**
	 * searches the given list given its type
	 * @param listType the type of the list {manual, sorted, unsorted}
	 * @param key the element key to find
	 * @return the result of the search
	 */
	public Integer AVLSearch(String listType, int key) {
		AbstractList<Integer> list = listGetter(listType);
		AbstractSearch<Integer, IntegerCompartor> search =
			new AVLSearch<Integer, IntegerCompartor>(list,
					new IntegerCompartor());
		Integer result = search.find(key);
		runningTime = search.runningTime();
		comparisons = search.getComparisons();
		return result;
	}

	/**
	 * searches the given list given its type
	 * @param listType the type of the list {manual, sorted, unsorted}
	 * @param key the element key to find
	 * @return the result of the search
	 */
	public Integer BSTSearch(String listType, int key) {
		AbstractList<Integer> list = listGetter(listType);
		AbstractSearch<Integer, IntegerCompartor> search =
			new BSTSearch<Integer, IntegerCompartor>(list,
					new IntegerCompartor());
		Integer result = search.find(key);
		runningTime = search.runningTime();
		comparisons = search.getComparisons();
		return result;
	}

	/**
	 * searches the given list given its type
	 * @param listType the type of the list {manual, sorted, unsorted}
	 * @param key the element key to find
	 * @return the result of the search
	 */
	public Integer interpolationSearch(String listType, int key) {
		AbstractList<Integer> list = listGetter(listType);
		AbstractSearch<Integer, IntegerCompartor> search =
				new InterpolationSearch<Integer, IntegerCompartor>(list,
					new IntegerCompartor());
		Integer result = search.find(key);
		runningTime = search.runningTime();
		comparisons = search.getComparisons();
		return result;
	}

	/**
	 * searches the given list given its type
	 * @param listType the type of the list {manual, sorted, unsorted}
	 * @param key the element key to find
	 * @return the result of the search
	 */
	public Integer binarySearch(String listType, int key) {
		AbstractList<Integer> list = listGetter(listType);
		AbstractSearch<Integer, IntegerCompartor> search =
				new BinarySearch<Integer, IntegerCompartor>(list,
					new IntegerCompartor());
		Integer result = search.find(key);
		runningTime = search.runningTime();
		comparisons = search.getComparisons();
		return result;
	}

	/**
	 * @return the running time of the last search operation done.
	 */
	public Long runningTime() {
		return runningTime;
	}

	/**
	 * @return the number of comparisons done in the last search operation
	 */
	public Integer getComparisons() {
		return comparisons;
	}

	/**
	 * finds all the elements in the given list type
	 * @param listType
	 * @param element
	 * @return the element array currently in the list
	 */
	public Object[] sequentialSearchAll(String listType, int element) {
		AbstractList<Integer> list = listGetter(listType);
		return new SequentialSearch<Integer, IntegerCompartor>(list,
				new IntegerCompartor()).findAll(element);
	}
}
