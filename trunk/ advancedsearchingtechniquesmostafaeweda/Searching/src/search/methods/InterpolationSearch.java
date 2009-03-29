package search.methods;

import java.util.Comparator;

import list.AbstractList;
import list.UnsortedList;

/**
 * Interpolates the list supposing linear normal distribution
 * of the sorted list at which it operates on
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
public class InterpolationSearch<E, K extends Comparator<E>> extends
		AbstractSearch<E, K> {

	public InterpolationSearch(AbstractList<E> list, K comp) {
		super(list, comp);
	}

	@Override
	public E find(E e) {
		// Returns index of toFind in sortedArray, or -1 if not found
		long start = System.nanoTime();
		int found = findAt(e);
		runningTime = System.nanoTime() - start;
		if (found == -1)
			return null;
		else
			return list.get(found);
	}

	private int findAt(E e) {
		int low = 0,
		mid; // the expected number
		 // number of comparisons made
		int high = list.size() - 1;
		comparisons = 1;
		while (comp.compare(e, list.get(low)) > 0 && comp.compare(e, list.get(high)) <= 0) {
			comparisons++;
			mid = low + (comp.compare(e, list.get(low)) * (high - low))
					/ (comp.compare(list.get(high), list.get(low)));
			if (comp.compare(e, list.get(mid)) > 0)
				low = mid + 1;
			else if (comp.compare(e, list.get(mid)) < 0)
				// Repetition of the comparison code is forced by syntax limitations.
				high = mid - 1;
			else
				return mid;
		}
		if (comp.compare(e, list.get(low)) == 0)
			return low;
		else
			return -1; // Not found
	}

	@Override
	public Object[] findAll(E e) {
		long start = System.nanoTime();
		int found = findAt(e);
		if (found == -1)
			return new Object[0];
		AbstractList<E> temp = new UnsortedList<E>(10);
		temp.add(list.get(found));
		int move = found - 1;
		comparisons++;
		while (move >= 0 && comp.compare(e, list.get(move)) == 0) {
			comparisons++;
			temp.add(list.get(move--));
		}
		move = found + 1;
		int n = list.size();
		comparisons++;
		while (move < n && comp.compare(e, list.get(move)) == 0) {
			comparisons++;
			temp.add(list.get(move++));
		}
		runningTime = System.nanoTime() - start;
		Object[] result = new Object[temp.size()];
		for (int i = 0; i < result.length; i++)
			result[i] = temp.get(i);;
		return result;
	}
}
