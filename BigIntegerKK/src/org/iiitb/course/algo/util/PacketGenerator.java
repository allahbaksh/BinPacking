package org.iiitb.course.algo.util;

import java.math.BigInteger;
import java.util.Random;

public class PacketGenerator {
	/**
	 * Number of packets to be generated
	 */
	private int numberOfPackets;

	/**
	 * Random number given to the packet
	 */
	private BigInteger numbers[];
	/**
	 * Size of of packet
	 */
	private int packetSize;
	/**
	 * Random number
	 */
	private Random random = new Random();

	public PacketGenerator(BigInteger[] inNumbers, int inNumberOfPackets) {
		numberOfPackets = inNumberOfPackets;
		numbers = inNumbers;
		packetSize = inNumbers.length / numberOfPackets;
	}

	/**
	 * Generate the packets.
	 * 
	 * @return
	 */
	public Packet[] generatePackets() {

		Packet[] packets = new Packet[numberOfPackets];

		for (int i = 0; i < numberOfPackets - 1; i++) {

			packets[i] = generatePacket();
		}

		packets[numberOfPackets - 1] = generateLastPacket();

		return packets;
	}

	/**
	 * Generates a packet
	 * 
	 * @return
	 */
	private Packet generatePacket() {
		int max = numbers.length - 1, min = 0;
		Element[] elements = new Element[packetSize];
		for (int i = 0; i < elements.length; i++) {
			int slot = random.nextInt(max - min);
			while (numbers[slot] == BigInteger.valueOf(-1)) {
				slot = random.nextInt(max - min) + 1;
			}
			BigInteger integer = numbers[slot];
			numbers[slot] = BigInteger.valueOf(-1);
			Element element = new Element(integer, null, null, false);
			elements[i] = element;
		}
		Packet packet = new Packet(elements);
		return packet;
	}

	/**
	 * Calculate the package size
	 * 
	 * @return
	 */
	private int calculatePacketSize() {
		return numbers.length / numberOfPackets;

	}

	/**
	 * Last packet consist of more elements and other packets
	 * 
	 * @return
	 */
	private Packet generateLastPacket() {
		// System.out.println("packet " + (numberOfPackets - 1));
		packetSize = packetSize + numbers.length % numberOfPackets;
		Element[] elements = new Element[packetSize];
		for (int i = 0, j = 0; i < numbers.length; i++) {
			if (numbers[i] != BigInteger.valueOf(-1)) {
				Element element = new Element(numbers[i], null, null, false);

				elements[j] = element;
				j++;
			}

		}
		Packet packet = new Packet(elements);
		return packet;
	}
}
