/**
 * 
 */
package org.iiitb.course.algo.util;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Allahbaksh_Asadullah
 * 
 */
public final class ProjectUtils {

	private ProjectUtils() {

	}

	public static final BigInteger calculateSetDifference(
			List<Element> leftArray, List<Element> rightArray) {
		BigInteger leftArraySum = BigInteger.ZERO;
		BigInteger rightArraySum = BigInteger.ZERO;
		BigInteger setDifference = BigInteger.ZERO;

		Iterator<Element> leftIter = leftArray.iterator();
		for (int i = 0; i < leftArray.size(); i++) {
			leftArraySum = leftArraySum.add(leftIter.next().getNumber());
		}

		Iterator<Element> rightIter = rightArray.iterator();
		for (int i = 0; i < rightArray.size(); i++) {
			rightArraySum = rightArraySum.add(rightIter.next().getNumber());
		}

		setDifference = leftArraySum.subtract(rightArraySum);

		return setDifference.abs();

	}

	public static final void printArrayElements(List<Element> listOfElements,
			String message) {
		// System.out.print(message+ "  ");
		// for (Iterator<Element> iterator = listOfElements.iterator(); iterator
		// .hasNext();) {
		// Element element = iterator.next();
		// System.out.print(element.getNumber() + " , ");
		// }
		// System.out.println();
	}

	public static final void printArrayElements(Element[] listOfElements,
			String message) {
		// System.out.print(message+ "  ");
		// for (int i=0; i < listOfElements.length;i++) {
		// System.out.print(listOfElements[i].getNumber() + " , ");
		// }
		// System.out.println();
	}

	// Handy function to create Big Int arrays
	public static BigInteger[] createBigIntArr(int r1, int r2) {
		if (r1 < 1 || r1 > 14)
			r1 = 1;
		if (r2 < 1 || r2 > 14)
			r2 = 14;
		if (r2 < r1) {
			// swap
			int r = r2;
			r2 = r1;
			r1 = r;
		}
		int reqd = (r2 - r1) + 1;
		BigInteger[] arr = new BigInteger[reqd];

		// only 9 zeros possible for int due to its size of 4 bytes
		// int i1 = 1000000000; //OK, 9 zeros
		// int i2 = 10000000000; //10 zeros gives error
		for (int r = r1; r <= r2; r++) {
			/*
			 * String s1 = "" + Math.pow(10,r); System.out.println(s1 + ", " +
			 * s1.length()); arr[r-r1] = new BigInteger(s1.substring(0,
			 * s1.length()-2));
			 */

			// arr[r-r1] = new BigInteger("" + (int)Math.pow(10,r));

			char[] chArr = new char[r];
			Arrays.fill(chArr, '0');
			String s = new String(chArr);

			arr[r - r1] = new BigInteger("1" + s);
		}
		return arr;
	}

	public static String convertArrayToString(BigInteger[] integers) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < integers.length; i++) {
			buffer.append(integers[i] + ",");
		}
		String temp = buffer.toString();
		int index = temp.lastIndexOf(",");
		temp = temp.substring(0, index) + "\n";
		return temp;
	}

	/**
	 * Get the sum array element array.
	 * 
	 * @param arrayElement
	 * @return
	 */
	public static BigInteger getSumOfElements(Element[] arrayElement) {
		BigInteger sum = BigInteger.ZERO;
		for (int i = 0; i < arrayElement.length; i++) {
			sum = sum.add(arrayElement[i].getNumber());
		}

		return sum;
	}

	/**
	 * Get the sum array excluding first element array. FIXME Move it to Util
	 * 
	 * @param List
	 *            of element
	 * @return
	 */
	public static BigInteger getSumOfElements(List<Element> elementList) {
		BigInteger sum = BigInteger.ZERO;
		for (int i = 01; i < elementList.size(); i++) {
			sum = sum.add(elementList.get(i).getNumber());
		}

		return sum;
	}

}
