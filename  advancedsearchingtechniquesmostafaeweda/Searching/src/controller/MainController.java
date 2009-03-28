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

public class MainController {

	private AbstractList<Integer> manualList;
	private AbstractList<Integer> sortedList;
	private AbstractList<Integer> unsortedList;
	private long runningTime;
	private int comparisons;

	public AbstractList<Integer> generateSortedList(int size) {
		AbstractList<Integer> list = new UnsortedList<Integer>(size);
		Random random = new Random();
		list.add(Math.abs(random.nextInt()) % 20);
		for (int i = 1; i < size; i++)
			list.add(list.get(i - 1) + Math.abs(random.nextInt() % 20) + 1);
		return sortedList = list;
	}

	public AbstractList<Integer> generateUnsortedList(int size) {
		AbstractList<Integer> list = new UnsortedList<Integer>(size);
		Random random = new Random();
		for (int i = 0; i < size; i++)
			list.add(Math.abs(random.nextInt() % ( 20 * size)) + 1);
		return unsortedList = list;
	}

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

	public AbstractList<Integer> generateManualList(int size) {
		return manualList = new UnsortedList<Integer>(size);
	}

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

	public Long runningTime() {
		return runningTime;
	}

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

	public Integer getComparisons() {
		return comparisons;
	}
}
