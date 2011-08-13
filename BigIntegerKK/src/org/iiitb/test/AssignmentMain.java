/**
 * 
 */
package org.iiitb.test;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author Allahbaksh_Asadullah
 * 
 */
public class AssignmentMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// createAssignment100();
		createStepByStep();
	}

	public static void createStepByStep() {
		long time = new Date().getTime();
		try {
			// Range 10
			createRangeFiles(22, 40, 1, new BigInteger("10"), 100, 1);
			// Range 100 10^2
			createRangeFiles(19, 40, 1, new BigInteger("100"), 100, 2);
			// Range 1000 10^3
			createRangeFiles(44, 70, 1, new BigInteger("1000"), 100, 3);
			// Range 10,000 10^4
			createRangeFiles(63, 80, 1, new BigInteger("10000"), 100, 4);
			// Range 10,0000 10^5
			createRangeFiles(75, 95, 1, new BigInteger("100000"), 100, 5);

			// Range 10,00000 10^6
			createRangeFiles(125, 140, 1, new BigInteger("1000000"), 100, 6);
			// Range 10^7
			createRangeFiles(215, 240, 1, new BigInteger("10000000"), 100, 7);
			// Range 10^8
			createRangeFiles(230, 250, 1, new BigInteger("100000000"), 100, 8);
			// Range 10^9
			createRangeFiles(360, 410, 1, new BigInteger("1000000000"), 100, 9);
			// Range 10^10
			createRangeFiles(480, 540, 1, new BigInteger("10000000000"), 100,
					10);
			// Range 10^11
			createRangeFiles(560, 620, 1, new BigInteger("100000000000"), 100,
					11);
			// Range 10^12
			createRangeFiles(860, 940, 1, new BigInteger("1000000000000"), 100,
					12);
			// Range 10^13
			createRangeFiles(950, 1050, 1, new BigInteger("10000000000000"),
					100, 13); // Range 10^14
			createRangeFiles(1250, 1300, 1, new BigInteger("100000000000000"),
					100, 14);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Time take is " + (new Date().getTime() - time));
	}

	public static void createAssignment100() {
		long time = new Date().getTime();
		try {
			// Range 10
			createRangeFiles(5, 50, 1, new BigInteger("10"), 100, 1);
			// Range 100 10^2
			createRangeFiles(5, 80, 2, new BigInteger("100"), 100, 2);
			// Range 1000 10^3
			createRangeFiles(5, 90, 3, new BigInteger("1000"), 100, 3);
			// Range 10,000 10^4
			createRangeFiles(6, 100, 3, new BigInteger("10000"), 100, 4);
			// Range 10,0000 10^5
			createRangeFiles(10, 200, 5, new BigInteger("100000"), 100, 5);

			// Range 10,00000 10^6
			createRangeFiles(10, 200, 5, new BigInteger("1000000"), 100, 6);
			// Range 10^7
			createRangeFiles(20, 250, 5, new BigInteger("10000000"), 100, 7);
			// Range 10^8
			createRangeFiles(20, 400, 10, new BigInteger("100000000"), 100, 8);
			// Range 10^9
			createRangeFiles(40, 510, 10, new BigInteger("1000000000"), 100, 9);
			// Range 10^10
			createRangeFiles(60, 650, 20, new BigInteger("10000000000"), 100,
					10);
			// Range 10^11
			createRangeFiles(80, 850, 30, new BigInteger("100000000000"), 100,
					11);
			// Range 10^12
			createRangeFiles(100, 1250, 40, new BigInteger("1000000000000"),
					100, 12);
			// Range 10^13
			createRangeFiles(150, 1300, 50, new BigInteger("10000000000000"),
					100, 13); // Range 10^14
			createRangeFiles(200, 1800, 50, new BigInteger("100000000000000"),
					100, 14);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Time take is " + (new Date().getTime() - time));
	}

	public static void createRangeFiles(int start, int end, int stepSize,
			BigInteger range, int noOfIteration, int filePrefix)
			throws IOException {
		final RandomNumberGenerator generator = new RandomNumberGenerator();
		final String fileName = "assignment_Step/100Iter_Step_" + filePrefix
				+ ".csv";
		FileWriter writer = new FileWriter(fileName);
		writer.write("Number,Set Diff,Time Taken\n");
		for (int n = start; n <= end; n += stepSize) {
			for (int i = 0; i < noOfIteration; i++) {
				// TODO remove hard coding when integer has different values.
				BigInteger randomIntegers[] = generator.generateRandomIntegers(
						n, range);
				KKAlgorithmImpl impl = new KKAlgorithmImpl(randomIntegers);
				impl.constructAndCalculateSetDiff();
				writer.write(n + "," + impl.differenceOfTwoSets + ","
						+ impl.time + "\n");
			}
			System.out.println("Number is  " + n + ", Range is  "
					+ range.toString());
			writer.flush();

		}
		writer.close();
		System.out.println("Range " + range.toString()
				+ " finished successfully");
		// Espace net

	}

}
