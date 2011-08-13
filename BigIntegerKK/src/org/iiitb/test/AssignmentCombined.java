/**
 * 
 */
package org.iiitb.test;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

import org.iiitb.course.algo.CKKAlgorithmImpl;
import org.iiitb.course.algo.KKAlgorithmImpl;
import org.iiitb.course.algo.PKKAlgorithmImpl;

/**
 * @author Allahbaksh_Asadullah
 * 
 */
public class AssignmentCombined {
	/**
	 * This is partial test. This is introduced because 11 Aug Discussion with
	 * Prof.
	 * 
	 * @param start
	 * @param end
	 * @param stepSize
	 * @param range
	 * @param noOfIteration
	 * @param filePrefix
	 * @throws IOException
	 */
	public static void applyAlgoForRange14(int start, int end, int stepSize,
			BigInteger range, int noOfIteration, int filePrefix)
			throws IOException {
		final RandomNumberGenerator generator = new RandomNumberGenerator();
		final String fileName = "assignment_final_14/100Iter_Step_"
				+ filePrefix + ".csv";
		FileWriter writer = new FileWriter(fileName);
		writer.write("Number,KK Diff, KK Time Taken, PKK Diff, PKK time, CKK Diff, CKK Time\n");

		for (int n = start; n <= end; n += stepSize) {
			System.out.println("Running algo for set size " + n);
			long time1 = System.currentTimeMillis();
			int counter = 0;
			for (int i = 0; i < noOfIteration; i++) {

				// TODO remove hard coding when integer has different values.
				BigInteger randomIntegers[] = generator.generateRandomIntegers(
						n, range);
				KKAlgorithmImpl impl = new KKAlgorithmImpl(randomIntegers);

				impl.constructAndCalculateSetDiff();
				BigInteger setDiff = impl.getSetDiff();

				writer.write(n + "," + impl.getSetDiff() + ","
						+ impl.getTimeSpent());

				boolean isSetDiffGreater = checkForSetDiffCondition(setDiff);
				if (isSetDiffGreater) {
					counter++;
					System.out.println("The n is " + n + " and iteration is "
							+ counter + "Total Number of iteration are " + i);
					pkk(randomIntegers, writer, n);
					ckk(randomIntegers, writer, n);
				} else {
					writer.write(",0,0,0,0");
				}
				writer.write("\n");
				// Come out of this when there are 100 CKK.
				if (counter > 100) {
					break;
				}
			}

			System.out.println("---took "
					+ (System.currentTimeMillis() - time1) + " milli seconds");

			writer.flush();

		}
		writer.close();
		System.out.println("Range " + range.toString()
				+ " finished successfully");
		System.out.println(fileName + " is created!");
		// Espace net

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// This code has been introduced to check how CKK behave for 10^14 when
		// number is small
		try {
			applyAlgoForRange14(20, 70, 10, new BigInteger("100000000000000"),
					1000, 14);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// if (args.length == 0) {
		// System.out
		// .println("Pls specify the range (2-14) for which you like to run the algo");
		// return;
		// }
		// Integer i1 = Integer.parseInt(args[0]);
		// if (i1 == null)
		// i1 = 0;
		// if (i1 < 2 || i1 > 14) {
		// System.out.println("Invalid range: " + args[0]);
		// return;
		// }
		// applyForRange(i1);
	}

	public static void createStepByStep() {
		long time = new Date().getTime();
		try {
			// Range 10
			applyAlgoForRange(22, 40, 1, new BigInteger("10"), 100, 1);
			// Range 100 10^2
			applyAlgoForRange(19, 40, 1, new BigInteger("100"), 100, 2);
			// Range 1000 10^3
			applyAlgoForRange(44, 70, 1, new BigInteger("1000"), 100, 3);
			// Range 10,000 10^4
			applyAlgoForRange(63, 80, 1, new BigInteger("10000"), 100, 4);
			// Range 10,0000 10^5
			applyAlgoForRange(75, 95, 1, new BigInteger("100000"), 100, 5);

			// Range 10,00000 10^6
			applyAlgoForRange(125, 140, 1, new BigInteger("1000000"), 100, 6);
			// Range 10^7
			applyAlgoForRange(215, 240, 1, new BigInteger("10000000"), 100, 7);
			// Range 10^8
			applyAlgoForRange(230, 250, 1, new BigInteger("100000000"), 100, 8);
			// Range 10^9
			applyAlgoForRange(360, 410, 1, new BigInteger("1000000000"), 100, 9);
			// Range 10^10
			applyAlgoForRange(480, 540, 1, new BigInteger("10000000000"), 100,
					10);
			// Range 10^11
			applyAlgoForRange(560, 620, 1, new BigInteger("100000000000"), 100,
					11);
			// Range 10^12
			applyAlgoForRange(860, 940, 1, new BigInteger("1000000000000"),
					100, 12);
			// Range 10^13
			applyAlgoForRange(950, 1050, 1, new BigInteger("10000000000000"),
					100, 13); // Range 10^14
			applyAlgoForRange(1250, 1300, 1, new BigInteger("100000000000000"),
					100, 14);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Time take is " + (new Date().getTime() - time));
	}

	public static void applyForRange(int range) {
		long time = new Date().getTime();
		try {
			switch (range) {
			// Range 10
			case 1:
				applyAlgoForRange(5, 50, 1, new BigInteger("10"), 100, 1);
				break;
			// Range 100 10^2
			case 2:
				applyAlgoForRange(5, 80, 2, new BigInteger("100"), 100, 2);
				break;

			// Range 1000 10^3
			case 3:
				applyAlgoForRange(5, 90, 3, new BigInteger("1000"), 100, 3);
				break;

			// Range 10,000 10^4
			case 4:
				applyAlgoForRange(6, 100, 3, new BigInteger("10000"), 100, 4);
				break;

			// Range 10,0000 10^5
			case 5:
				applyAlgoForRange(10, 200, 5, new BigInteger("100000"), 100, 5);
				break;

			// Range 10,00000 10^6
			case 6:
				applyAlgoForRange(10, 200, 5, new BigInteger("1000000"), 100, 6);
				break;

			// Range 10^7
			case 7:
				applyAlgoForRange(20, 250, 5, new BigInteger("10000000"), 100,
						7);
				break;

			// Range 10^8
			case 8:
				applyAlgoForRange(20, 400, 10, new BigInteger("100000000"),
						100, 8);
				break;

			// Range 10^9
			case 9:
				applyAlgoForRange(40, 510, 10, new BigInteger("1000000000"),
						100, 9);
				break;

			// Range 10^10
			case 10:
				applyAlgoForRange(60, 650, 20, new BigInteger("10000000000"),
						100, 10);
				break;

			// Range 10^11
			case 11:
				applyAlgoForRange(80, 850, 30, new BigInteger("100000000000"),
						100, 11);
				break;

			// Range 10^12
			case 12:
				applyAlgoForRange(100, 1250, 40,
						new BigInteger("1000000000000"), 100, 12);
				break;
			// Range 10^13
			case 13:
				applyAlgoForRange(150, 1300, 50, new BigInteger(
						"10000000000000"), 100, 13); // Range 10^14
				break;

			case 14:
				applyAlgoForRange(200, 1300, 50, new BigInteger(
						"100000000000000"), 100, 14);
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Time take is " + (new Date().getTime() - time));
	}

	public static void applyAlgoForRange(int start, int end, int stepSize,
			BigInteger range, int noOfIteration, int filePrefix)
			throws IOException {
		final RandomNumberGenerator generator = new RandomNumberGenerator();
		final String fileName = "assignment_final/100Iter_Step_" + filePrefix
				+ ".csv";
		FileWriter writer = new FileWriter(fileName);
		writer.write("Number,KK Diff, KK Time Taken, PKK Diff, PKK time, CKK Diff, CKK Time\n");

		for (int n = start; n <= end; n += stepSize) {
			System.out.println("Running algo for set size " + n);
			long time1 = System.currentTimeMillis();
			for (int i = 0; i < noOfIteration; i++) {
				// TODO remove hard coding when integer has different values.
				BigInteger randomIntegers[] = generator.generateRandomIntegers(
						n, range);
				KKAlgorithmImpl impl = new KKAlgorithmImpl(randomIntegers);

				impl.constructAndCalculateSetDiff();
				BigInteger setDiff = impl.getSetDiff();

				// writer.write(n + "," + impl.differenceOfTwoSets + ","
				// + impl.time + ",KK\n");
				//
				writer.write(n + "," + impl.getSetDiff() + ","
						+ impl.getTimeSpent());

				boolean isSetDiffGreater = checkForSetDiffCondition(setDiff);
				if (isSetDiffGreater) {
					pkk(randomIntegers, writer, n);
					ckk(randomIntegers, writer, n);
				} else {
					writer.write(",0,0,0,0");
				}
				writer.write("\n");
			}
			// System.out.println("Number is  " + n + ", Range is  "
			// + range.toString());
			System.out.println("---took "
					+ (System.currentTimeMillis() - time1) + " milli seconds");

			writer.flush();

		}
		writer.close();
		System.out.println("Range " + range.toString()
				+ " finished successfully");
		System.out.println(fileName + " is created!");
		// Espace net

	}

	private static void pkk(BigInteger[] randomIntegers, FileWriter writer,
			int n) throws IOException {
		BigInteger diff = null;
		long time = 0; // let us sum up all times

		for (int i = 0; i < 100; i++) {
			BigInteger[] integers = Arrays.copyOf(randomIntegers,
					randomIntegers.length);

			PKKAlgorithmImpl impl = new PKKAlgorithmImpl(integers, 2);
			impl.constructAndCalculateSetDiff();
			time += impl.getTimeSpent();
			if (diff == null) {
				diff = impl.getSetDiff();
			} else {
				if (diff.compareTo(impl.getSetDiff()) == 1) {

					// System.out.println("Swap " + diff.toString()
					// + " New number is " + impl.getSetDiff().toString());
					diff = impl.getSetDiff();
				}
			}

		}
		// writer.write(n + "," + diff + "," + time + ",PKK\n");
		writer.write("," + diff + "," + time);
	}

	private static void ckk(BigInteger[] randomIntegers, FileWriter writer,
			int n) throws IOException {
		// System.out.println("Entering CKK");
		CKKAlgorithmImpl impl = new CKKAlgorithmImpl(randomIntegers);
		impl.constructNode();
		// size, time, bestdifff, howmany soln, timed-out
		// writer.write(n + "," + impl.getBestSoln().setDiff + ","
		// + impl.getElapsedTime() + ",CKK\n");
		//
		writer.write("," + impl.getBestSoln().setDiff + ","
				+ impl.getElapsedTime());

		// writer.flush();
	}

	private static boolean checkForSetDiffCondition(BigInteger integer) {
		if (integer.compareTo(BigInteger.ONE) == 1) {
			return true;
		}
		return false;
	}

}
