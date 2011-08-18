/**
 * 
 */
package org.iiitb.test.ckk;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

import org.iiitb.course.algo.KKAlgorithmImpl;
import org.iiitb.test.RandomNumberGenerator;

/**
 * @author Allahbaksh_Asadullah
 * 
 */
public class KKTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	private static void createRangeNumber20Range14() throws IOException {
		FileWriter writer = new FileWriter("Range14Number20.csv");

		RandomNumberGenerator randomGenerator = new RandomNumberGenerator();
		for (int i = 0; i < 100; i++) {
			BigInteger integers[] = randomGenerator.generateRandomIntegers(20,
					new BigInteger("100000000000000"));
			BigInteger pkkCopy[] = Arrays.copyOf(integers, integers.length);
			KKAlgorithmImpl impl = new KKAlgorithmImpl(integers);
			impl.constructAndCalculateSetDiff();

		}
		writer.close();
	}

}
