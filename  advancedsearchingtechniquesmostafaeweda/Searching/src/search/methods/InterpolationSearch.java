package search.methods;

import java.util.Comparator;

import list.AbstractList;

public class InterpolationSearch<E, K extends Comparator<E>> extends
		AbstractSearch<E, K> {

	public InterpolationSearch(AbstractList<E> list, K comp) {
		super(list, comp);
	}

	@Override
	public E find(E e) {
		// Returns index of toFind in sortedArray, or -1 if not found
		int low = 0,
			mid; // the expected number
			 // number of comparisons made
		int high = list.size() - 1;
		long start = System.nanoTime();
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
				return list.get(mid);
		}
		runningTime = System.nanoTime() - start;
		if (comp.compare(e, list.get(low)) == 0)
			return list.get(low);
		else
			return null; // Not found
	}

	@Override
	public E[] findAll(E e) {
		return null;
	}
}
