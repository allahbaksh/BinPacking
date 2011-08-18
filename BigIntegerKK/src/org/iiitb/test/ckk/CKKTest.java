/**
 * 
 */
package org.iiitb.test.ckk;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

import org.iiitb.course.algo.CKKAlgorithmImpl;
import org.iiitb.course.algo.CKKAlgorithmImpl.SolnSet;
import org.iiitb.course.algo.PKKAlgorithmImpl;
import org.iiitb.test.RandomNumberGenerator;

/**
 * @author Allahbaksh_Asadullah
 * 
 */
public class CKKTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			createRangeNumber8Range14();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void dummyTest() {
		int[] intArray = { 18, 10, 9, 9, 8, 7, 6 };
		BigInteger integers[] = new BigInteger[intArray.length];
		for (int i = 0; i < intArray.length; i++) {
			integers[i] = new BigInteger("" + intArray[i]);
		}
		CKKAlgorithmImpl impl = new CKKAlgorithmImpl(integers, true);
		impl.constructNode();

		try {
			System.out.println("");
		} catch (Exception e) {
		} finally {

		}
	}

	private static BigInteger pkk(BigInteger[] randomIntegers)
			throws IOException {
		BigInteger diff = null;

		for (int i = 0; i < 100; i++) {
			BigInteger[] integers = Arrays.copyOf(randomIntegers,
					randomIntegers.length);

			PKKAlgorithmImpl impl = new PKKAlgorithmImpl(integers, 2);
			impl.constructAndCalculateSetDiff();

			if (diff == null) {
				diff = impl.getSetDiff();
			} else {
				if (diff.compareTo(impl.getSetDiff()) == 1) {

					diff = impl.getSetDiff();
				}
			}

		}

		return diff;
	}

	private static void createRangeNumber8Range14() throws IOException {
		FileWriter writer = new FileWriter("RangeNumber8.csv");
		FileWriter writer2 = new FileWriter("RangeNumber8Diff.csv");
		writer2.write("PKK Diff,CKK Diff,Num1,Num2,Num3,Num4,Num5,Num6,Num7,Num8\n");
		RandomNumberGenerator randomGenerator = new RandomNumberGenerator();
		for (int i = 0; i < 10000; i++) {
			BigInteger integers[] = randomGenerator.generateRandomIntegers(8,
					new BigInteger("100000000000000"));
			BigInteger pkkCopy[] = Arrays.copyOf(integers, integers.length);
			BigInteger printCopy[] = Arrays.copyOf(integers, integers.length);
			CKKAlgorithmImpl impl = new CKKAlgorithmImpl(integers, false);
			impl.constructNode();
			BigInteger pkkInt = pkk(pkkCopy);
			BigInteger ckkInt = impl.differenceOfTwoSets;
			if (pkkInt.compareTo(ckkInt) == -1) {

				String number = "";
				for (int j = 0; j < printCopy.length; j++) {
					number += printCopy[j].toString() + ",";
				}
				writer.write(number + "\n");
				writer2.write(pkkInt.toString() + "," + ckkInt.toString() + ","
						+ number + "\n");
				writer.flush();
				writer2.flush();

			}

		}
		writer.close();
		writer2.close();
	}

	private static void createRangeNumber20Range14() throws IOException {
		FileWriter writer = new FileWriter("Range14Number20.csv");
		// writer.write("Actul,CKK Set Diff,Sum Set1 CKK, Sum Set2 CKK,"
		// + "Best PKK Set Diff\n");
		writer.write("Actul,CKK Set Diff,PKK Set Diff\n");
		RandomNumberGenerator randomGenerator = new RandomNumberGenerator();
		for (int i = 0; i < 10000; i++) {
			BigInteger integers[] = randomGenerator.generateRandomIntegers(20,
					new BigInteger("100000000000000"));
			BigInteger pkkCopy[] = Arrays.copyOf(integers, integers.length);
			CKKAlgorithmImpl impl = new CKKAlgorithmImpl(integers, false);
			impl.constructNode();

			SolnSet set = impl.getBestSoln();
			// BigInteger integer1 =
			// ProjectUtils.getSumOfElements(set.getList1());
			// BigInteger integer2 =
			// ProjectUtils.getSumOfElements(set.getList2());
			writer.write(impl.differenceOfTwoSets + "");// + ","
			// + set.getSetDifference() + "," + integer1 + "," + integer2);
			pkk(pkkCopy, writer);
			if (i / 10 == 0) {
				writer.flush();
			}

		}
		writer.close();
	}

	private static void pkk(BigInteger[] randomIntegers, FileWriter writer)
			throws IOException {
		BigInteger diff = null;

		for (int i = 0; i < 100; i++) {
			BigInteger[] integers = Arrays.copyOf(randomIntegers,
					randomIntegers.length);

			PKKAlgorithmImpl impl = new PKKAlgorithmImpl(integers, 2);
			impl.constructAndCalculateSetDiff();

			if (diff == null) {
				diff = impl.getSetDiff();
			} else {
				if (diff.compareTo(impl.getSetDiff()) == 1) {

					diff = impl.getSetDiff();
				}
			}

		}

		writer.write("," + diff + "\n");
	}

}
