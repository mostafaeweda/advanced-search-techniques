package list;

import java.util.Comparator;

public class SortedList<E> extends AbstractList<E> {

	public SortedList(int size, Comparator<E> comp) {
		super(size);
		this.comp = comp;
	}

	@SuppressWarnings("unchecked")
	public void add(E e) {
		if (size == length)
			doubleSize();
		int i;
		for(i = 0; i < size && comp.compare(e, (E)elements[i]) > 0;i++);
		if (i < size) {
			for (int j = size - 1; j >= i;j--)
				elements[j+1] = elements[j];
			elements[i] = e;
			size++;
		}
		else
			elements[size++] = e;
	}
}
