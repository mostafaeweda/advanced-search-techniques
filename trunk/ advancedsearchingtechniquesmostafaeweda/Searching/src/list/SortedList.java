package list;

public class SortedList<E> extends AbstractList<E> {

	public SortedList(int size) {
		super(size);
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
		}
		elements[size++] = e;
	}
}
