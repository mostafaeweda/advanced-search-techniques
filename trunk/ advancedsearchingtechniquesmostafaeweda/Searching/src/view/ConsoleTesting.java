package view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import list.AbstractList;
import search.compartors.IntegerCompartor;
import search.methods.AVLSearch;
import search.methods.AbstractSearch;
import search.methods.BSTSearch;
import search.methods.BinarySearch;
import search.methods.InterpolationSearch;
import search.methods.SequentialSearch;
import controller.MainController;

/**
 * Implementation of test items that are used to analyze the data for the charts
 * 
 * @author Mostafa Mahmoud Mahmoud Eweda
 * @version 1.0
 * @since JDK 1.6
 * 
 * @see MainWindow
 * for graphical user interface operations
 *
 */
public class ConsoleTesting {

	private static final int CONST_STEP = 100;
	private static int SIZE = 100;
	private static int STEP = 1;

	private MainController controller;
	private PrintWriter reportWriter;
	private StringBuffer avgBuffer;
	private PrintWriter averageWriter;

	public void run() throws IOException {
		avgBuffer = new StringBuffer();
		controller = new MainController();
		reportWriter = new PrintWriter(new BufferedWriter(new FileWriter(
				"report.csv")));
		averageWriter = new PrintWriter(new BufferedWriter(new FileWriter(
				"charts.csv")));
		avgBuffer.append("\n,,Sorted Searching,,,,,,Unsorted Searching,,");
		avgBuffer.append("\n\nlist Size, Sequential, Binary, BST, AVL, Interpolation, ,Sequential, BST, AVL\n");
		for (int i = 1; i <= 60; i++) {
			try {
				System.out.println(i);
				SIZE = i * CONST_STEP;
				AbstractList<Integer> sorted = controller
						.generateSortedList(SIZE);
				AbstractList<Integer> unsorted = controller
						.generateUnsortedList(SIZE);
				testSorted(sorted);
				testUnsorted(unsorted);
				averageWriter.print(avgBuffer.toString());
			} catch (Throwable e1) {
				e1.printStackTrace();
				System.out.println(i);
			}
			avgBuffer = new StringBuffer();
		}
		reportWriter.close();
		averageWriter.close();
	}

	public void sequentialSearch(AbstractList<Integer> list) {
		reportWriter.println("\nSequential Search");
		AbstractSearch<Integer, IntegerCompartor> search = new SequentialSearch<Integer, IntegerCompartor>(
				list, new IntegerCompartor());
		avgBuffer.append(", ");
		testAll(list, search);
	}

	private void testAll(AbstractList<Integer> list,
			AbstractSearch<Integer, IntegerCompartor> search) {
		int worstComparisons = 0;
		int compars;
		long maxRun = 0;
		long runningTime;
		long allRun = 0, allCopmar = 0;
		reportWriter.println("Item, Running Time, Numer of comparisons");
		for (int i = 0, n = list.size(); i < n; i += STEP) {
			search.find(list.get(i));
			runningTime = search.runningTime();
			compars = search.getComparisons();
			allRun += runningTime;
			allCopmar += compars;
			reportWriter.println(list.get(i) + ", " + runningTime + ", "
					+ compars);
			if (compars > worstComparisons)
				worstComparisons = compars;
			if (runningTime > maxRun)
				maxRun = runningTime;
		}
		reportWriter
				.println("\nMax numer of comparisons, average comparisons,"
						+ "Max running time in nano seconds, Max running time in milli seconds, average time in nanos");
		reportWriter.println(worstComparisons + ", " + allCopmar
				/ (list.size() / STEP) + ", " + maxRun + "," + maxRun / 1000000
				+ ", " + allRun / (list.size() / STEP));
		avgBuffer.append(allCopmar / (list.size() / STEP));
	}

	public void AVLSearch(AbstractList<Integer> list) {
		AbstractSearch<Integer, IntegerCompartor> search = new AVLSearch<Integer, IntegerCompartor>(
				list, new IntegerCompartor());
		reportWriter.println("\n\nAVL Search");
		avgBuffer.append(", ");
		testAll(list, search);
	}

	public void BSTSearch(AbstractList<Integer> list) {
		AbstractSearch<Integer, IntegerCompartor> search = new BSTSearch<Integer, IntegerCompartor>(
				list, new IntegerCompartor());
		reportWriter.println("\n\nBST Search");
		avgBuffer.append(", ");
		testAll(list, search);
	}

	public void interpolationSearch(AbstractList<Integer> list) {
		AbstractSearch<Integer, IntegerCompartor> search = new InterpolationSearch<Integer, IntegerCompartor>(
				list, new IntegerCompartor());
		reportWriter.println("\n\nInterpolation Search");
		avgBuffer.append(", ");
		testAll(list, search);
	}

	public void binarySearch(AbstractList<Integer> list) {
		AbstractSearch<Integer, IntegerCompartor> search = new BinarySearch<Integer, IntegerCompartor>(
				list, new IntegerCompartor());
		reportWriter.println("\n\nBinary Search");
		avgBuffer.append(", ");
		testAll(list, search);
	}

	private void testUnsorted(AbstractList<Integer> unsorted) {
		reportWriter.println("Unsorted Searching");
		reportWriter.println();
		sequentialSearch(unsorted);
		BSTSearch(unsorted);
		AVLSearch(unsorted);
		avgBuffer.append("\n");
	}

	private void testSorted(AbstractList<Integer> sorted) {
		reportWriter.println("Unsorted Searching");
		reportWriter.println();
		avgBuffer.append(SIZE);
		sequentialSearch(sorted);
		binarySearch(sorted);
		BSTSearch(sorted);
		AVLSearch(sorted);
		interpolationSearch(sorted);
		avgBuffer.append(", ");
	}

	public static void main(String[] args) throws IOException {
		new ConsoleTesting().run();
	}
}
