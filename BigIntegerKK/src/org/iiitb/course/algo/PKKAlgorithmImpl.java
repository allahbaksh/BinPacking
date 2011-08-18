/**
 * 
 */
package org.iiitb.course.algo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.iiitb.course.algo.util.Element;
import org.iiitb.course.algo.util.Packet;
import org.iiitb.course.algo.util.PacketGenerator;

/**
 * @author Allahbaksh_Asadullah
 * 
 */
public class PKKAlgorithmImpl {

	private List<Element> listA;
	private List<Element> listB;
	protected BigInteger setDifference;
	private List<List<Element>> listOfListA;
	private List<List<Element>> listOfListB;
	protected long time;
	protected int numberOfPackets;

	private Packet[] packet;

	/**
	 * This is set to false. Meaning we need not backtrack. Just we can find out
	 * the set difference and leave it.
	 */
	private boolean backTrack = false;

	public PKKAlgorithmImpl(BigInteger[] integers, int numberOfPacket) {
		PacketGenerator generator = new PacketGenerator(integers,
				numberOfPacket);
		packet = generator.generatePackets();
		time = new Date().getTime();
		numberOfPackets = numberOfPacket;
		listOfListA = new ArrayList<List<Element>>();
		listOfListB = new ArrayList<List<Element>>();
	}

	public PKKAlgorithmImpl(BigInteger[] integers, int numberOfPacket,
			boolean inBackTrack) {
		this(integers, numberOfPacket);
		backTrack = inBackTrack;

	}

	/**
	 * This is the main method. It actually takes Elements and finds gives back
	 * two sets.
	 * 
	 * @param elements
	 */
	public void constructAndCalculateSetDiff() {
		listA = new ArrayList<Element>();
		listB = new ArrayList<Element>();
		// FIXME For num ber of packet greater than 2 we need to fix
		BigInteger setDiff[] = new BigInteger[2];
		for (int i = 0; i < numberOfPackets; i++) {

			KKAlgorithmImpl impl = new KKAlgorithmImpl(
					packet[i].getPacketElements());
			impl.constructAndCalculateSetDiff();
			listOfListA.add(impl.listA);
			listOfListB.add(impl.listB);
			setDiff[i] = impl.differenceOfTwoSets;
			// listA.addAll(impl.listA);
			// listB.addAll(impl.listB);
		}
		setDifference = (setDiff[0].subtract(setDiff[1])).abs();
		time = new Date().getTime() - time;
		// createMinSetDiff();
		// setDifference = ProjectUtils.calculateSetDifference(listA, listB);
	}

	private void createMinSetDiff() {
		BigInteger sumAD = sumOfTwoList(listOfListA.get(0), listOfListB.get(1));
		BigInteger sumBC = sumOfTwoList(listOfListB.get(0), listOfListA.get(1));
		BigInteger diff1 = sumAD.subtract(sumBC);
		BigInteger sumAB = sumOfTwoList(listOfListA.get(0), listOfListB.get(0));
		BigInteger sumCD = sumOfTwoList(listOfListB.get(1), listOfListA.get(1));
		BigInteger diff2 = sumCD.subtract(sumAB);
		BigInteger absDiff1 = diff1.abs();
		BigInteger absDiff2 = diff2.abs();

		int switchTo = absDiff1.compareTo(absDiff2);
		switch (switchTo) {
		case -1:
			setDifference = absDiff1;
			break;
		case 0:
			setDifference = absDiff1;
			break;
		case 1:
			setDifference = absDiff2;
			break;

		}

	}

	/*
	 * Consider A and B being one of the set so A-B should be nearly equal to
	 * zero. Similary C and D being one of the set so C-D should be nearly equal
	 * to zero We can safely say that A-D = B-C as A
	 */

	private BigInteger sumOfTwoList(final List<Element> aElements,
			final List<Element> bElements) {
		BigInteger sum = new BigInteger("0");
		for (Iterator<Element> iterator = bElements.iterator(); iterator
				.hasNext();) {
			Element element = (Element) iterator.next();
			sum = sum.add(element.getNumber());
		}

		for (Iterator<Element> iterator = aElements.iterator(); iterator
				.hasNext();) {
			Element element = iterator.next();
			sum = sum.add(element.getNumber());

		}

		return sum;
	}

	public BigInteger getSetDiff() {
		return setDifference;
	}

	public long getTimeSpent() {
		return time;
	}

}
