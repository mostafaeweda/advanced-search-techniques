package search.methods;

import java.util.Comparator;

import list.AbstractList;


public class BinarySearch <E, K extends Comparator<E>> extends AbstractSearch<E,K> {

	public BinarySearch(AbstractList<E> list, K comp) {
		super(list, comp);
	}

	@Override
	public E find(E e) {
		int lower = 0, upper = list.size() - 1;
		int mid, comparison;
		comparisons = 0;
		long start = System.nanoTime();
		while (lower <= upper) {
			mid = (lower + upper) / 2;
			comparison = comp.compare(e, list.get(mid));
			comparisons++;
			if (comparison < 0) {
				// e < mid
				upper = mid - 1;
			}
			else if (comparison > 0) {
				// e > mid
				lower = mid + 1;
			}
			else { // found
				runningTime = System.nanoTime() - start;
				return list.get(mid);
			}
		}
		runningTime = System.nanoTime() - start;
		return null;
	}

	@Override
	public E[] findAll(E e) {
		return null;
	}
}
