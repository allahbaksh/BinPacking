/**
 * 
 */
package org.iiitb.course.algo;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.iiitb.course.algo.util.Element;
import org.iiitb.course.algo.util.ElementComparator;
import org.iiitb.course.algo.util.ProjectUtils;
import org.iiitb.test.RandomNumberGenerator;

/**
 * @author Allahbaksh_Asadullah
 * 
 */
public class CKKAlgorithmImpl {

	private Element[] elements;
	// private ArrayList<SolnSet> solns = new ArrayList<SolnSet>();

	// 60 seconds is better!
	private static final long TIME_LIMIT = 1000 * 60;

	long time;
	long solnCount;
	boolean doExit = false;

	public static class SolnSet {
		List<Element> list1;
		List<Element> list2;
		public BigInteger setDiff;
	}

	SolnSet lastBestSet;

	public CKKAlgorithmImpl() {
	}

	public CKKAlgorithmImpl(Element[] inElements) {
		elements = inElements;
		time = new Date().getTime();
	}

	/**
	 * Constructor which takes BigInteger.
	 * 
	 * @param integers
	 */
	public CKKAlgorithmImpl(BigInteger[] integers) {
		elements = new Element[integers.length];
		for (int i = 0; i < integers.length; i++) {
			elements[i] = new Element(integers[i], null, null, false);
		}
		Arrays.sort(elements, new ElementComparator());
		time = new Date().getTime();
	}

	private void constructNode(final Element[] elementArray) {
		if (doExit == true)
			return;

		constructLeftNode(elementArray);
		constructRightNode(elementArray);
	}

	public void constructNode() {
		time = System.currentTimeMillis();// new Date().getTime();

		constructNode(elements);
	}

	private void constructLeftNode(final Element[] elementArray) {
		if (!isValidLeftNode(elementArray)) {
			return;
		}
		BigInteger firstNumber = elementArray[0].getNumber();
		BigInteger secondNumber = elementArray[1].getNumber();
		BigInteger diffOfNumber = firstNumber.subtract(secondNumber);
		Element newElement = new Element(diffOfNumber, elementArray[0],
				elementArray[1], true);
		Element[] newElementArray = Arrays.copyOfRange(elementArray, 1,
				elementArray.length);
		newElementArray[0] = newElement;

		// MergeSortElements sort = new MergeSortElements(newElementArray);
		// sort.sort(newElementArray);
		Arrays.sort(newElementArray, new ElementComparator());

		// printElementArr(newElementArray);
		constructNode(newElementArray);
	}

	private void constructRightNode(final Element[] elementArray) {
		// if (!isValidRightNode(elementArray)) {
		// return;
		// }

		// if there are two or less elements in the input tree,
		// then no use to constuct right node tree
		if (elementArray.length <= 2)
			return;

		BigInteger firstNumber = elementArray[0].getNumber();
		BigInteger secondNumber = elementArray[1].getNumber();
		BigInteger sumOfNumber = firstNumber.add(secondNumber);

		// dont construct any tree if this is not a valid right node!
		BigInteger restSum = sumNminusTwo(elementArray);
		if (sumOfNumber.compareTo(restSum) >= 0)
			return;

		Element newElement = new Element(sumOfNumber, elementArray[0],
				elementArray[1], true);
		Element[] newElementArray = Arrays.copyOfRange(elementArray, 1,
				elementArray.length);
		newElementArray[0] = newElement;

		Arrays.sort(newElementArray, new ElementComparator());

		constructNode(newElementArray);
	}

	private boolean isValidRightNode(Element[] arrayElement) {
		if (arrayElement.length == 1) {
			// if(arrayElement[0].getNumber()==2){
			backTrack(arrayElement[0]);
			// }
			return false;
		}

		BigInteger sum = getSumOfElements(arrayElement);

		// Do pruning
		int val = arrayElement[0].getNumber().compareTo(sum);
		if (val >= 0) {
			return false;
		}
		return true;
	}

	/**
	 * Get the sum array excluding first element array. FIXME Move it to Util
	 * 
	 * @param arrayElement
	 * @return
	 */
	private static BigInteger getSumOfElements(Element[] arrayElement) {
		BigInteger sum = new BigInteger("0");
		for (int i = 1; i < arrayElement.length; i++) {
			sum = sum.add(arrayElement[i].getNumber());
		}
		// System.out.println("Sum " + sum);
		return sum;
	}

	/**
	 * Get sum excluding first two elements. FIXME FIXME Move it to Util
	 * 
	 * @param arrayElement
	 * @return
	 */
	private static BigInteger sumNminusTwo(Element[] arrayElement) {
		BigInteger sum = new BigInteger("0");
		for (int i = 2; i < arrayElement.length; i++) {
			sum = sum.add(arrayElement[i].getNumber());
		}
		// System.out.println("Sum " + sum);
		return sum;
	}

	private boolean isValidLeftNode(Element[] arrayElement) {
		if (arrayElement.length > 1) {

			return true;
		}
		// if(arrayElement[0].getNumber()==2){
		backTrack(arrayElement[0]);
		// }
		return false;

	}

	public void printElementArr(Element[] elem) {
		for (int i = 0; i < elem.length; i++) {
			System.out.print(elem[i].getNumber() + " ,");
		}
		System.out.println();
	}

	public void backTrack(Element element) {
		// System.out.println("Back tracking");
		ArrayList<Element> list1 = new ArrayList<Element>();
		ArrayList<Element> list2 = new ArrayList<Element>();
		Element parent1 = element.getParent1();
		Element parent2 = element.getParent2();
		list1.add(parent1);
		list2.add(parent2);
		while (hasBar(list1, list2) || hasBar(list2, list1)) {

		}
		// System.out.println("Solution: " + (++solnCount));
		// System.out.print("Array List 1: ");
		// printArrayList(list1);
		// System.out.print("Array List 2: ");
		// printArrayList(list2);

		solnCount++;
		BigInteger diff1 = ProjectUtils.calculateSetDifference(list1, list2);
		// System.out.println("Solution: " + (solnCount) + ", Set Diff: " +
		// diff1);

		if ((lastBestSet != null && diff1.compareTo(lastBestSet.setDiff) < 0)
				|| (lastBestSet == null)) {

			lastBestSet = new SolnSet();
			lastBestSet.list1 = list1;
			lastBestSet.list2 = list2;
			lastBestSet.setDiff = diff1;
		}

		// if the time exceeds the limit, stop
		// long t1 = new Date().getTime();
		long t1 = System.currentTimeMillis();
		if ((t1 - time) > TIME_LIMIT) {
			time = t1 - time;
			// exit
			doExit = true;
			// System.out.println("Timed out");
			// System.out.println("------------");
			return;
		}
		if (diff1.compareTo(BigInteger.ONE) == 0
				|| diff1.compareTo(BigInteger.ZERO) == 0) {
			time = t1 - time;
			doExit = true;
			// System.out.println("Soln found ");
			// System.out.println("------------");
			return;
		}
	}

	public long getElapsedTime() {
		if (doExit)
			return time;
		time = new Date().getTime() - time;
		return time;
	}

	private boolean hasBar(ArrayList<Element> list1, ArrayList<Element> list2) {
		Iterator<Element> iter = list1.iterator();
		while (iter.hasNext()) {
			Element tempElement = iter.next();
			if (tempElement.isBar()) {
				list1.remove(tempElement);
				list1.add(tempElement.getParent1());
				list2.add(tempElement.getParent2());
				return true;
			}

		}
		return false;
	}

	public SolnSet getBestSoln() {
		// SolnSet[] newArray = Arrays.copyOfRange(solns.toArray(new
		// SolnSet[0]),
		// 0, solns.size());
		//
		// Arrays.sort(newArray, new SolnComparator());
		// return newArray[0];
		return lastBestSet;
	}

	public long getSolnCount() {
		return solnCount;
	}

	public class SolnComparator implements Comparator<SolnSet> {

		@Override
		public int compare(SolnSet soln1, SolnSet soln2) {
			BigInteger o1 = soln1.setDiff;
			BigInteger o2 = soln2.setDiff;

			switch (o1.compareTo(o2)) {
			case -1:
				return 1;
			case 1:
				return -1;
			case 0:
				return 0;

			default:
				break;
			}

			return o1.compareTo(o2);
		}
	}

	// Prints the array list
	private void printArrayList(ArrayList<Element> list) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Element element = (Element) iterator.next();
			System.out.print(element.getNumber() + " , ");
		}
		System.out.println();
	}

	public static BigInteger[] getBigInts(int arr[]) {
		BigInteger[] new1 = new BigInteger[arr.length];
		for (int i = 0; i < arr.length; i++)
			new1[i] = new BigInteger(new String("") + arr[i]);

		return new1;
	}

	/**
	 * For testing purpose
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int nums[] = { 15, 9, 8, 7, 6, 5, 4 };
		BigInteger numbers[] = getBigInts(nums);

		int r1 = 10;
		int r2 = 11;
		int maxSetSize = 210;// it needs to be 2000
		int maxIter = 5;// it needs to be 10000

		final BigInteger range[] = ProjectUtils.createBigIntArr(r1, r2);

		for (int r = r1; r <= r2; r++) {

			try {
				final String fileName = "ckkRange" + r + ".csv";
				FileWriter writer = new FileWriter(fileName);
				writer.write("Number,Time Taken, BestSetDiff, noOfSolns, timedout\n");

				RandomNumberGenerator generator = new RandomNumberGenerator();
				BigInteger range1 = range[r - r1];

				// To plot the graph we need to have many data points.
				for (int n = 200; n < maxSetSize; n++) {

					// for each data point, repeat the experiment for maxIter
					// times
					for (int j2 = 1; j2 <= maxIter; j2++) {

						System.out.println("Set Size " + n + ", Iter " + j2);
						BigInteger[] bigInts = generator
								.generateRandomIntegers(n, range1);

						CKKAlgorithmImpl ckk = new CKKAlgorithmImpl(bigInts);
						ckk.constructNode();

						// TODO Write the following to the file
						// size, time, bestdifff, howmany soln, timed-out
						writer.write(n + "," + ckk.getElapsedTime() + ","
								+ ckk.getBestSoln().setDiff + ", "
								+ ckk.getSolnCount() + ","
								+ (ckk.doExit ? "1" : "0") + "\n");
					}
					// System.out.println("range " + r + ", size " + n
					// + " complete");
				}
				// System.out.println("range " + r + " done");
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
