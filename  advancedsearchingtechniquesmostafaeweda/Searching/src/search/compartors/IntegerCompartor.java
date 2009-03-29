package search.compartors;

import java.util.Comparator;

/**
 * Implementation of an integer comparator that compares two integers
 * and returns an integer representation of the possible difference between
 * them when put in lists
 * 
 * @author Mostafa Mahmoud Mahmoud Eweda
 * @version 1.0
 * @since JDK 1.6
 * 
 * @see Comparator
 *
 */
public class IntegerCompartor implements Comparator<Integer> {

	/**
	 * @see Comparator#compare(Object, Object)
	 */
	@Override
	public int compare(Integer o1, Integer o2) {
		return o1 - o2;
	}

}
