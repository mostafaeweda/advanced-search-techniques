package list;

import java.util.Comparator;
import java.util.NoSuchElementException;

import search.methods.AbstractSearch;

public abstract class AbstractList<E> {

	protected Object[] elements;
	protected int size;
	protected int length;
	protected Comparator<E> comp;
	private AbstractSearch<E, Comparator<E>> search;

	public AbstractList(int length) {
		this.size = 0;
		elements = new Object[length];
	}

	public E find(E e) {
		return search.find(e);
	}

	public E[] findAll(E e) {
		return search.findAll(e);
	}
	
	public void add(E e) {
		if (size == length)
			doubleSize();
		elements[size++] = e;
	}

	public int getLength() {
		return length;
	}

	protected void doubleSize() {
		Object[] newElements = new Object[2 * length];
		for (int i = 0; i < length; i++)
			newElements[i] = elements[i];
		length = 2 * length;
		elements = newElements;
	}
	
	@SuppressWarnings("unchecked")
	public E get(int i) {
		if (i < size)
			return (E) elements[i];
		throw new NoSuchElementException();
	}

	public void setSearch(AbstractSearch<E, Comparator<E>> search) {
		this.search = search;
	}

	public int size() {
		return size;
	}
}
