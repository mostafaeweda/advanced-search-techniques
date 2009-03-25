package search.methods;

import java.util.Comparator;

import list.AbstractList;


public class InterpolationSearch<E, K extends Comparator<E>> extends AbstractSearch<E, K> {

	public InterpolationSearch(AbstractList<E> list, K comp) {
		super(list, comp);
	}

	@Override
	public E find(E e) {
		return null;
	}

	@Override
	public E[] findAll(E e) {
		return null;
	}

}
