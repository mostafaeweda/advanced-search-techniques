package search.methods;

import static org.junit.Assert.*;
import list.AbstractList;
import list.SortedList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import search.compartors.IntegerCompartor;

public class InterpolationTest {

	private static AbstractSearch<Integer, IntegerCompartor> search;;
	private static AbstractList<Integer> list;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		list = new SortedList<Integer>(10, new IntegerCompartor());
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(8);
		list.add(9);
		list.add(10);
		search = new InterpolationSearch<Integer, IntegerCompartor>(list, new IntegerCompartor());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFind() {
		assertEquals(null, search.find(0));
		System.out.println(search.getComparisons());
		assertEquals(new Integer(1), search.find(1));
		System.out.println(search.getComparisons());
		assertEquals(null, search.find(2));
		System.out.println(search.getComparisons());
		assertEquals(new Integer(3), search.find(3));
		System.out.println(search.getComparisons());
		assertEquals(new Integer(4), search.find(4));
		System.out.println(search.getComparisons());
		assertEquals(new Integer(5), search.find(5));
		System.out.println(search.getComparisons());
		assertEquals(new Integer(6), search.find(6));
		System.out.println(search.getComparisons());
		assertEquals(null, search.find(7));
		System.out.println(search.getComparisons());
		assertEquals(new Integer(8), search.find(8));
		System.out.println(search.getComparisons());
		assertEquals(new Integer(9), search.find(9));
		System.out.println(search.getComparisons());
		assertEquals(new Integer(10), search.find(10));
		System.out.println(search.getComparisons());
		assertEquals(null, search.find(11));
		System.out.println(search.getComparisons());
	}

}
