package search.methods;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import list.AbstractList;
import list.UnsortedList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import search.compartors.IntegerCompartor;

public class AVLSearchTest {

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
		list = new UnsortedList<Integer>(10);
		list.add(4);
		list.add(3);
		list.add(3);
		/*list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);*/
		search = new AVLSearch<Integer, IntegerCompartor>(list, new IntegerCompartor());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFind() {
		System.out.println(search.find(2));
		assertEquals(new Integer(3), search.find(3));
		assertEquals(new Integer(4), search.find(4));
		assertEquals(null, search.find(11));
	}

	@Test
	public void testFindAll() {
		assertArrayEquals(new Object[]{3, 3}, search.findAll(3));
	}
}
