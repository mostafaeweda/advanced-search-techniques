package search.methods;

import java.util.Comparator;

import list.AbstractList;
import list.UnsortedList;

/**
 * Iterates sequentially on the list items to check if the wanted elements exists or not
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
	public Object[] findAll(E e) {
		long start = System.nanoTime();
		UnsortedList<E> temp = new UnsortedList<E>(10);
		for (int i = 0, n = list.size(); i < n; i++) {
			E found = list.get(i);
			if (comp.compare(e, found) == 0)
				temp.add(found);
		}
		comparisons = list.size();
		runningTime = System.nanoTime() - start;
		int n = temp.size();
		Object[] result = new Object[n];
		for (int i = 0; i < n; i++)
			result[i] = temp.get(i);
		return result;
	}

}
