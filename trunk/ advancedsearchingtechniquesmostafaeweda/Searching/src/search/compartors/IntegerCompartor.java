package search.compartors;

import java.util.Comparator;

public class IntegerCompartor implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		return o1 - o2;
	}

}
