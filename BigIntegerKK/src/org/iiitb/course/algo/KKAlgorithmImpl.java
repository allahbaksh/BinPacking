/**
 * 
 */
package org.iiitb.course.algo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Stack;

import org.iiitb.course.algo.util.Element;
import org.iiitb.course.algo.util.ElementComparator;
import org.iiitb.course.algo.util.ProjectUtils;

/**
 * @author Allahbaksh_Asadullah
 * 
 */
public class KKAlgorithmImpl {
	/**
	 * List of element in set1. This is useful for later computing some stuff
	 */
	protected ArrayList<Element> listA;
	/**
	 * List of element in set2. This is useful for later computing some stuff
	 */
	protected ArrayList<Element> listB;
	/**
	 * This can be a long or integer.
	 */
	protected BigInteger differenceOfTwoSets;
	/**
	 * Time taken for computation
	 */
	long time;

	private Element[] elements;

	/**
	 * This constructor is usually called from PKK and in CKK
	 * 
	 * @param inElements
	 */
	public KKAlgorithmImpl(Element[] inElements) {
		elements = inElements;
	}

	public KKAlgorithmImpl(BigInteger[] integers) {
		elements = new Element[integers.length];
		for (int i = 0; i < integers.length; i++) {
			elements[i] = new Element(integers[i], null, null, false);
		}

		time = new Date().getTime();
	}

	/**
	 * This is the main method.
	 * 
	 * @param elements
	 */
	public void constructAndCalculateSetDiff() {
		// Start of time
		ProjectUtils.printArrayElements(elements,
				"Random Elements before sorting");
		Arrays.sort(elements, new ElementComparator());
		ProjectUtils.printArrayElements(elements,
				"Random Elements after sorting");
		Stack<Element> elementStack = new Stack<Element>();
		while (elements.length != 2) {
			elements = findDifference(elements, elementStack);
		}
		reconstructElement(elements[0], elements[1], elementStack);
		// End time
		time = new Date().getTime() - time;
	}

	/**
	 * Back track and construct the two sets of numbers
	 * 
	 * @param a
	 * @param b
	 * @param stack
	 */
	protected void reconstructElement(Element a, Element b, Stack<Element> stack) {
		ArrayList<Element> elementsA = new ArrayList<Element>();
		ArrayList<Element> elementsB = new ArrayList<Element>();
		elementsA.add(a);
		elementsB.add(b);

		// Till All element
		while (!stack.isEmpty()) {
			Element stackElement = stack.peek();

			printDetailsOfStackElement(stackElement);

			for (int j = 0; j < elementsA.size(); j++) {
				// If element is bar then replace
				Element elementOfA = elementsA.get(j);
				if (elementOfA.isBar()) {
					if (elementOfA.getNumber() == stackElement.getNumber()) {
						elementsA.remove(elementOfA);
						elementsA.add(stackElement.getParent1());
						elementsB.add(stackElement.getParent2());
						stack.pop();
						break;
					}
				}

			}

			ProjectUtils.printArrayElements(elementsA, "Elements of A");

			if (stack.isEmpty()) {
				break;
			}
			stackElement = stack.peek();
			printDetailsOfStackElement(stackElement);

			for (int j = 0; j < elementsB.size(); j++) {
				// If element is bar then replace
				Element elementOfB = elementsB.get(j);
				if (elementOfB.isBar()) {
					if (elementOfB.getNumber() == stackElement.getNumber()) {
						elementsB.remove(elementOfB);
						elementsB.add(stackElement.getParent1());
						elementsA.add(stackElement.getParent2());
						stack.pop();
						break;
					}
				}

			}

			ProjectUtils.printArrayElements(elementsB, "Elements of B");

		}

		ProjectUtils.printArrayElements(elementsA, "Element of Set A ");
		ProjectUtils.printArrayElements(elementsB, "Element of Set B");
		listA = elementsA;
		listB = elementsB;
		BigInteger setDifference = ProjectUtils.calculateSetDifference(
				elementsA, elementsB);
		differenceOfTwoSets = setDifference;
		// System.out.println("Set Difference is  " + setDifference);

	}

	/**
	 * This method find the difference between first two elements of the array
	 * and return new array which is sorted and added with the bar element
	 * 
	 * @param elements
	 * @param elementStack
	 * @return
	 */
	protected Element[] findDifference(Element[] elements,
			Stack<Element> elementStack) {

		BigInteger diff = elements[0].getNumber().subtract(
				elements[1].getNumber());

		Element difference = new Element(diff, elements[0], elements[1], true);

		elementStack.push(difference);

		Element[] newElements = new Element[elements.length - 1];
		newElements[0] = difference;
		for (int i = 1, j = 2; i < newElements.length; i++, j++) {
			newElements[i] = elements[j];
		}
		Arrays.sort(newElements, new ElementComparator());
		return newElements;

	}

	public BigInteger getSetDiff() {
		return differenceOfTwoSets;
	}

	public long getTimeSpent() {
		return time;
	}

	/**
	 * This method is kept to print the element on Stack. This required for
	 * testing purpose. In final code just comment Sysout
	 * 
	 * @param stackElement
	 */
	protected static void printDetailsOfStackElement(Element stackElement) {
		// System.out.println("Stack Element is " + stackElement.getNumber()
		// + " is Bar " + stackElement.isBar() + "\n parent A "
		// + stackElement.getParent1().getNumber() + "\t parent B "
		// + stackElement.getParent2().getNumber());
	}

}
