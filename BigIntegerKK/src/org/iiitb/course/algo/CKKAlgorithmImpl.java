/**
 * 
 */
package org.iiitb.course.algo;

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

/**
 * @author Allahbaksh_Asadullah
 * 
 */
public class CKKAlgorithmImpl {
	/**
	 * The elements
	 */
	private Element[] elements;
	/**
	 * This is kept right now to do testing
	 */
	private ArrayList<SolnSet> solns = new ArrayList<SolnSet>();

	/**
	 * Get solution which are available within 60sec. We need not count KK for
	 * this. i.e first iteration should be avoided
	 */
	private static final long TIME_LIMIT = 1000 * 60;
	/**
	 * This is set to false. Meaning we need not backtrack. Just we can find out
	 * the set difference and leave it.
	 */
	private boolean backTrack = false;

	private SolnSet lastBestSet;

	long time;
	long solnCount;
	/**
	 * This specifies whether we need exit from the program. This is set once
	 * 60sec or more than time is encountered
	 * 
	 */
	private boolean doExit = false;

	/**
	 * This can be a long or integer.
	 */
	protected BigInteger differenceOfTwoSets = null;

	public static class SolnSet {
		private List<Element> list1;
		private List<Element> list2;
		private BigInteger setDiff;

		public BigInteger getSetDifference() {
			return setDiff;
		}

		public List<Element> getList1() {
			return list1;
		}

		public List<Element> getList2() {
			return list2;
		}
	}

	/**
	 * Default constructor
	 */
	public CKKAlgorithmImpl() {
	}

	/**
	 * Sets back tracking
	 * 
	 * @param inBackTrack
	 */
	public CKKAlgorithmImpl(boolean inBackTrack) {
		backTrack = inBackTrack;
	}

	/**
	 * Specifies the Elements
	 * 
	 * @param inElements
	 */
	public CKKAlgorithmImpl(Element[] inElements) {
		elements = inElements;
		Arrays.sort(elements, new ElementComparator());
		time = new Date().getTime();
	}

	public CKKAlgorithmImpl(Element[] inElement, boolean inBackTrack) {
		this(inElement);
		backTrack = inBackTrack;
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

	public CKKAlgorithmImpl(BigInteger[] integers, boolean inBackTrack) {
		this(integers);
		backTrack = inBackTrack;
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
		if (sumOfNumber.compareTo(restSum) >= 0) {
			return;
		}
		Element newElement = new Element(sumOfNumber, elementArray[0],
				elementArray[1], true);
		Element[] newElementArray = Arrays.copyOfRange(elementArray, 1,
				elementArray.length);
		newElementArray[0] = newElement;

		Arrays.sort(newElementArray, new ElementComparator());

		constructNode(newElementArray);
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
		if (backTrack) {
			backTrack(arrayElement[0]);
		}
		BigInteger integer = arrayElement[0].getNumber().subtract(
				arrayElement[1].getNumber());
		if (differenceOfTwoSets == null) {
			differenceOfTwoSets = integer;
		}
		if (integer.compareTo(differenceOfTwoSets) == -1) {
			differenceOfTwoSets = integer;
		}

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

		// FIXME Add to array list to get all solution
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

}
