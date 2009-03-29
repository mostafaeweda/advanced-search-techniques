package list;

import java.util.Comparator;
import java.util.NoSuchElementException;

public abstract class AbstractList<E> {

	protected Object[] elements;
	protected int size;
	protected int length;
	protected Comparator<E> comp;

	public AbstractList(int length) {
		this.size = 0;
		this.length = length;
		elements = new Object[length];
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

	public int size() {
		return size;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Size: " + size);
		buffer.append("Length: " + length);
		for (Object element : elements) {
			buffer.append("\n" + element.toString());
		}
		return buffer.toString();
	}
}
