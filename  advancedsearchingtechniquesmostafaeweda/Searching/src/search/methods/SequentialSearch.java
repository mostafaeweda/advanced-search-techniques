package search.methods;

import java.util.Comparator;

import list.AbstractList;
import list.UnsortedList;

public class SequentialSearch<E, K extends Comparator<E>> extends AbstractSearch<E, K> {

	public SequentialSearch(AbstractList<E> list, K comp) {
		super(list, comp);
	}

	@Override
	public E find(E e) {
		long start = System.nanoTime();
		for (int i = 0, n = list.size(); i < n; i++) {
			E temp = list.get(i);
			if (comp.compare(e, temp) == 0) {
				comparisons = i + 1;
				runningTime = System.nanoTime() - start;
				return temp;
			}
		}
		runningTime = System.nanoTime() - start;
		return null;
	}

	@Override
	public E[] findAll(E e) {
		UnsortedList<E> temp = new UnsortedList<E>(10);
		for (int i = 0, n = list.size(); i < n; i++) {
			E found = list.get(i);
			if (comp.compare(e, found) == 0)
				temp.add(found);
		}
		return null;
	}

}
