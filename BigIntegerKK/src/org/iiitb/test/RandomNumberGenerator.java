/**
 * 
 */
package org.iiitb.test;

import java.math.BigInteger;
import java.util.Random;

import org.iiitb.course.algo.util.Element;

/**
 * @author Allahbaksh_Asadullah This class generates Random number
 * 
 */
public class RandomNumberGenerator {
	public RandomNumberGenerator() {

	}
	

	/**
	 * Generate a random number if the range is 10^9. This can be easily done
	 * using class Random
	 * 
	 * @param numberOfElements
	 * @param range
	 * @return
	 */
	public Element[] generateRandomElements(int numberOfElements,
			int range) {
		Element[] elements = new Element[numberOfElements];
		Random random = new Random();
		int max = range, min = 0;
		for (int i = 0; i < numberOfElements; i++) {
			int rand = random.nextInt(max - min + 1) + min;
			elements[i] = new Element(BigInteger.valueOf(rand), null, null,
					false);

		}
		return elements;
	}
	
	/**
	 * Generate element from range 10^10 to 10^14
	 * 
	 * @param numberOfElements
	 * @param range
	 * @return
	 */
	public Element[] generateRandomElements(int numberOfElements, BigInteger range) {
		Element[] elements = new Element[numberOfElements];

		for (int i = 0; i < numberOfElements; i++) {
			BigInteger randomBigInteger = nextRandomBigInteger(range);
			elements[i] = new Element(randomBigInteger, null, null, false);

		}
		return elements;
	}
	
	
	public BigInteger[] generateRandomIntegers(int numberOfElements, BigInteger range){
		BigInteger[] integers = new  BigInteger[numberOfElements];
		for (int i = 0; i < numberOfElements; i++) {
			BigInteger randomBigInteger = nextRandomBigInteger(range);
			integers[i] = randomBigInteger;

		}
		return integers;
	}

	/**
	 * Taken a simple approach as given in
	 * http://stackoverflow.com/questions/2290057
	 * /how-to-generate-a-random-biginteger-value-in-java
	 */
	protected BigInteger nextRandomBigInteger(BigInteger n) {
		Random rand = new Random();
		BigInteger result = new BigInteger(n.bitLength(), rand);
		while (result.compareTo(n) >= 0) {
			result = new BigInteger(n.bitLength(), rand);
		}
		return result;
	}
	/**
	 * Given a array of BigInteger generates element out of it. 
	 * @param integers
	 * @return
	 */
	public Element[] generateRandomElements(BigInteger[] integers){
		Element elements[] = new Element[integers.length];
		for (int i = 0; i < integers.length; i++) {
			elements[i] = new Element(integers[i], null, null, false);
		}
		return elements;
	}
	
	/**
	 * Given a array of int generates element out of it. 
	 * @param integers
	 * @return
	 */
	public Element[] generateRandomElements(int[] integers){
		Element elements[] = new Element[integers.length];
		for (int i = 0; i < integers.length; i++) {
			elements[i] = new Element(BigInteger.valueOf(integers[i]), null, null, false);
		}
		return elements;
	}




}
