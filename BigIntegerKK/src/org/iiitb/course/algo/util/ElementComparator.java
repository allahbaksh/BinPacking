/**
 * 
 */
package org.iiitb.course.algo.util;

import java.math.BigInteger;
import java.util.Comparator;

/**
 * @author Allahbaksh_Asadullah
 * 
 */
public class ElementComparator implements Comparator<Element> {

	@Override
	public int compare(Element integer1, Element integer2) {

		BigInteger o1 = integer1.getNumber();
		BigInteger o2 = integer2.getNumber();

		switch (o1.compareTo(o2)) {
		case -1:
			// System.out.println(o1.doubleValue() + " is less than "
			// + o2.doubleValue());
			return 1;

		case 0:
			// System.out.println(o1.doubleValue() + " is equal to "
			// // + o2.doubleValue());
			// if( integer1.getParent1() == null ) return -1;
			// if( integer2.getParent1() == null ) return 1;
			// int n1 = integer1.getParent1().getNumber().
			// compareTo(integer2.getParent1().getNumber());
			// return n1*-1;
			return 0;

		case 1:
			// System.out.println(o1.doubleValue() + " is greater than "
			// + o2.doubleValue());
			return -1;

		}
		return 0;
	}

}
