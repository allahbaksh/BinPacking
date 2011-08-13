/**
 * 
 */
package org.iiitb.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

import org.iiitb.course.algo.util.ProjectUtils;

/**
 * @author Allahbaksh_Asadullah
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//new Main().kkTest();
		// Need to call setDiffLessThanSix and setDiffGreaterTheSiz
//		 BigInteger range[] = { //new BigInteger("10"), new BigInteger("100"),
//		 new BigInteger("1000"), new BigInteger("10000"),
//		 new BigInteger("100000"), new BigInteger("1000000"),
//		 new BigInteger("10000000"), new BigInteger("100000000"),
//		 new BigInteger("1000000000"), new BigInteger("10000000000"),
//		 new BigInteger("100000000000"),
//		 new BigInteger("1000000000000"),
//		 new BigInteger("10000000000000"),
//		 new BigInteger("100000000000000") };
		 
		
		
//		 for (int i = 0; i < range.length; i++) {
//		 new Main().createRandomNumberFile(10, 2000, 1000, range[i],
//		 "DataRange"+range[i].toString());
//		 }
		BigInteger bs[] = ProjectUtils.createBigIntArr(2, 14);
		for( BigInteger b : bs)
			System.out.println(b);
	}

	// How many time experiment has to be repeated
	static final int REPEAT_COUNT = 10000;
	
	// Starting of number n
	static final int START_SIZE = 10;
	
	// Ending of number n
	static final int END_SIZE = 2000;
	
	//range - the power of 10
	static final int RANGE_START = 1;
	static final int RANGE_END = 14;

	

	public void kkTest() {
		final BigInteger range[] = { new BigInteger("10"),
//				new BigInteger("100"), new BigInteger("1000"),
//				new BigInteger("10000"), new BigInteger("100000"),
//				new BigInteger("1000000"), new BigInteger("10000000"),
//				new BigInteger("100000000"), new BigInteger("1000000000"),
//				new BigInteger("10000000000"), new BigInteger("100000000000"),
//				new BigInteger("1000000000000"),
//				new BigInteger("10000000000000"),
//				new BigInteger("100000000000000")
		};
		Date date = new Date();
		
		long startTime = date.getTime();
		try {
			
			StringBuffer buffer = new StringBuffer();
			RandomNumberGenerator generator = new RandomNumberGenerator();
		
			for (int i = 0; i < range.length; i++) {
			//	FileWriter randomNumberWriter = new FileWriter("Random-Ten"+range[i].toString());
//				FileWriter writer = new FileWriter("KKTest-Ten-"
//						+ range[i].toString(), true);
				for (int j = 10; j < 2000; j++) {
					
				for (int k = 0; k < 10; k++) {
						BigInteger[] integers = generator
								.generateRandomIntegers(j, range[i]);
						
					//	String strRandom =convertArrayToString(integers);
						//randomNumberWriter.append(strRandom);
						KKAlgorithmImpl imple = new KKAlgorithmImpl(integers);
						imple.constructAndCalculateSetDiff();
//						String str = j + "," + range[i].toString() + ","
//								+ imple.differenceOfTwoSets + "\n";
					//	writer.append(str);
					}

				//	writer.flush();
					//randomNumberWriter.flush();

				}

			//	writer.flush();
			//	randomNumberWriter.flush();
			//	writer.close();
			//	randomNumberWriter.flush();
			//	buffer = new StringBuffer();
				System.out.println("Range is " + range[i].toString());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = new Date().getTime();
		System.out.println("Time taken is "+ (endTime-startTime));
	}

	//This is unused at moment
	public void setDiffLessThanTenSix() {
		int range[] = { 10, 100, 1000, 10000, 100000, 1000000 };
		for (int i = 0; i < range.length; i++) {

			RandomNumberGenerator generator = new RandomNumberGenerator();
			// To plot the graph we need to have 2000 elements. We are doing it
			// from 2000
			for (int n = 10; n < 2000; n++) {
				// Repeat the experiment for 1000 times
				for (int j2 = 0; j2 < 1000; j2++) {
					BigInteger[] integers = generator.generateRandomIntegers(n,
							BigInteger.valueOf(range[i]));
					KKAlgorithmImpl kkImpl = new KKAlgorithmImpl(integers);
					kkImpl.constructAndCalculateSetDiff();

					// TODO Write the following to the file n,
					// kkImpl.differenceOfTwoSets., range
					// PKKAlgorithmImpl pkkImpl = new PKKAlgorithmImpl(n,
					// numberOfPacket)
					// Write result of PKK algorithm to different file
				}
			}
		}
	}

	//This is unused at moment
	public void setDiffLessGreaterThanTenSix() {
		// new BigInteger("10000000"),
		// from 10^7
		BigInteger range[] = { new BigInteger("10000000"),
				new BigInteger("100000000"), new BigInteger("1000000000"),
				new BigInteger("10000000000"), new BigInteger("100000000000"),
				new BigInteger("1000000000000"),
				new BigInteger("10000000000000"),
				new BigInteger("100000000000000") };
		// int range[] = {10,100,1000,10000,100000,1000000};
		for (int i = 0; i < range.length; i++) {

			RandomNumberGenerator generator = new RandomNumberGenerator();
			// To plot the graph we need to have 2000 elements.
			for (int n = 10; n < 2000; n++) {
				// Repeat the experiment for 1000 times
				for (int j2 = 0; j2 < 1000; j2++) {
					BigInteger[] integers = generator.generateRandomIntegers(n,
							range[i]);
					KKAlgorithmImpl kkImpl = new KKAlgorithmImpl(integers);
					kkImpl.constructAndCalculateSetDiff();

					// TODO Write the following to the file n,
					// kkImpl.differenceOfTwoSets., range
					// PKKAlgorithmImpl pkkImpl = new PKKAlgorithmImpl(n,
					// numberOfPacket)
					// Write result of PKK algorithm to different file

				}

			}
		}
	}
	
	void createRandomNumberFiles() {
		
		// Total range in which file will be generated.
		final BigInteger range[] = ProjectUtils.createBigIntArr(1, 14);

		//for each range we create one FILE
		 for (int i = 0; i < range.length; i++) {
			 createRandomNumberFile(START_SIZE, END_SIZE, REPEAT_COUNT, range[i],
			 "DataRange"+range[i].toString());		 
			}
	}

	/**
	 * This method creates a Random number file
	 * 
	 * @param from
	 *            10 in our case to 2000
	 * @param to
	 * @param times
	 *            10000 or thousand time or how many time the same step sould be
	 *            repeated with different dataset
	 * @param range
	 *            10, 100, 1000, 10^14
	 * @param fileName
	 *            File Name in which it should be written
	 * 
	 */
	public void createRandomNumberFile(int from, int to, int times,
			BigInteger range, String fileName) {
		try {
			FileWriter writer = new FileWriter(fileName);
			String str = "";
			StringBuffer buffer = new StringBuffer();

			RandomNumberGenerator generator = new RandomNumberGenerator();
			for (int i = from; i < to; i++) {
				int j = 0;
				// Keeping range, number n constant generate
				while (j < times) {
					BigInteger integers[] = generator.generateRandomIntegers(i,
							range);
					str = ProjectUtils.convertArrayToString(integers);
					buffer.append(str);
					j++;
					///if (range.compareTo(new BigInteger("1000")) == 1) {
					//	writer.write(str + "\n");
					//}
				}
				writer.write(buffer.toString() + "\n");
				writer.flush();
				buffer.setLength(0);

			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

//Range is 10
//Time taken is 393861 6.5 min