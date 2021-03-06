package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.scripting.elems.Element;
import hr.fer.zemris.java.custom.scripting.elems.ElementVariable;

/**
 * A node representing a single for-loop construct. It inherits from Node class.
 * 
 * @author Ante Spajic
 *
 */
public class ForLoopNode extends Node {

	/**
	 * Used for keeping variable of the for loop.
	 */
	private ElementVariable variable;
	/**
	 * Used for keeping start expression of the for loop.
	 */
	private Element startExpression;
	/**
	 * Used for keeping end expression of the for loop.
	 */
	private Element endExpression;
	/**
	 * Used for keeping step expressiopn of the for loop.
	 */
	private Element stepExpression;

	/**
	 * Class constructor for creating a for loop node with specified variable,
	 * start, end and step expressions.
	 * 
	 * @param variable
	 *            a valid {@link ElementVariable}
	 * @param startExpression
	 *            a valid {@link Element} that represents a start expression
	 * @param endExpression
	 *            a valid {@link Element} that represents a end expression
	 * @param stepExpression
	 *            a valid {@link Element} that represents a step expression, can
	 *            be null
	 * @throws IllegalArgumentException
	 *             if <code>variable</code>, <code>startExpression</code> or
	 *             <code>endExpression</code> are <code>null</code>
	 */
	public ForLoopNode(ElementVariable variable, Element startExpression,
			Element endExpression, Element stepExpression) {
		if (variable == null) {
			throw new IllegalArgumentException(
					"Variable of the for loop can't be null.");
		}
		if (startExpression == null) {
			throw new IllegalArgumentException(
					"Start expression of the for loop can't be null.");
		}
		if (endExpression == null) {
			throw new IllegalArgumentException(
					"End expression of the for loop can't be null.");
		}
		if (!startExpression.getClass().equals(endExpression.getClass())) {
			throw new IllegalArgumentException(
					"Start expression and end expression of the for loop must have a same type.");
		}
		this.variable = variable;
		this.startExpression = startExpression;
		this.endExpression = endExpression;
		this.stepExpression = stepExpression;
	}

	/**
	 * Getter method for variable property.
	 * 
	 * @return the variable
	 */
	public ElementVariable getVariable() {
		return variable;
	}

	/**
	 * Getter method for start expression property.
	 * 
	 * @return the startExpression
	 */
	public Element getStartExpression() {
		return startExpression;
	}

	/**
	 * Getter method for end expression property.
	 * 
	 * @return the endExpression
	 */
	public Element getEndExpression() {
		return endExpression;
	}

	/**
	 * Getter method for step expression property.
	 * 
	 * @return the stepExpression
	 */
	public Element getStepExpression() {
		return stepExpression;
	}

	/**
	 * Returns a text representation of the for loop node. {$ FOR + value of the
	 * variable + value of the start exp + value of the end exp + value of step
	 * exp (if not null) + $}
	 * 
	 * @example: @ FOR i 1 10 1 $}
	 * @return a text representation
	 */
	@Override
	public String asText() {
		String str = "{$ FOR " + variable.asText() + " " + startExpression.asText() + " " + endExpression.asText();
        if (stepExpression != null) {
            str += " " + stepExpression.asText();
        }
        str += " $}";
        for (int i = 0, n = numberOfChildren(); i < n; i++) {
            str += getChild(i).asText();
        }
        str += "{$END$}";
        return str;
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitForLoopNode(this);
	}

}
