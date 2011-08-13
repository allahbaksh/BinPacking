package org.iiitb.course.algo.util;

import java.math.BigInteger;

public class Element {
	private BigInteger number =BigInteger.valueOf(-1);
	private Element parent1 = null;
	private Element parent2 = null;
	private boolean isBar=false;
	
	
	public Element(BigInteger number, Element parent1, Element parent2, boolean isBar) {
		super();
		this.number = number;
		this.parent1 = parent1;
		this.parent2 = parent2;
		this.isBar = isBar;
	}
	
	
	public BigInteger getNumber() {
		return number;
	}
	public void setNumber(BigInteger number) {
		this.number = number;
	}
	public Element getParent1() {
		return parent1;
	}
	public void setParent1(Element parent1) {
		this.parent1 = parent1;
	}
	public Element getParent2() {
		return parent2;
	}
	public void setParent2(Element parent2) {
		this.parent2 = parent2;
	}
	public boolean isBar() {
		return isBar;
	}
	public void setBar(boolean isBar) {
		this.isBar = isBar;
	}

}
