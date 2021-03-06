package hr.fer.zemris.java.custom.scripting.elems;

import java.text.DecimalFormat;

/**
 * Class that extends Element and is used to store double elements used in echo
 * and forloop nodes.
 * 
 * @author Ante Spajic
 *
 */
public class ElementConstantDouble extends Element {

	private double value;

	/**
	 * Constructor for this element which sets its value.
	 * 
	 * @param value
	 *            Value to be set for this element.
	 */
	public ElementConstantDouble(double value) {
		this.value = value;
	}

	/**
	 * Getter for this constants value.
	 * 
	 * @return the Value of this constant
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Returns string representation of this element.
	 * 
	 */
	@Override
	public String asText() {
		DecimalFormat formatter = new DecimalFormat("###.###");
		return formatter.format(value);
	}
}
