package org.iiitb.course.algo.util;

/**
 * This is wrapper class which wraps the Elements
 * @author Allahbaksh_Asadullah This class is used PKK algorithm.
 */
public class Packet {

	Element[] packetElements;

	Packet(Element[] packetElements) {
		this.packetElements = packetElements;

	}

	public Element[] getPacketElements() {
		return packetElements;
	}

	public void setPacketElements(Element[] packetElements) {
		this.packetElements = packetElements;
	}

}
