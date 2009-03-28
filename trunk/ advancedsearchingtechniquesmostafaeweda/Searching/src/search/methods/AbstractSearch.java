package search.methods;

import java.util.Comparator;

import list.AbstractList;

public abstract class AbstractSearch<E, K extends Comparator<E>> {

	protected AbstractList<E> list;
	protected K comp;
	protected int comparisons;
	protected long runningTime;

	public AbstractSearch(AbstractList<E> list, K comp) {
		this.list = list;
		this.comp = comp;
	}

	public abstract E find(E e);

	public abstract Object[] findAll(E e);

	public int getComparisons() {
		return comparisons;
	}

	public long runningTime() {
		return runningTime;
	}
}
