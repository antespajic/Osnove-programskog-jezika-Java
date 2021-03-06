package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;

/**
 * Base class for all graph nodes.
 * 
 * @author Ante Spajic
 *
 */
public class Node {

	private ArrayIndexedCollection children;

	/**
	 * Adds given child to an internally managed collection of children.
	 * 
	 * @param child
	 *            A node object that is added
	 */
	public void addChildNode(Node child) {
		if (children == null) {
			children = new ArrayIndexedCollection();
		}

		children.add(child);
	}

	/**
	 * Returns how many children does a given node have.
	 * 
	 * @return number of (direct) children
	 */
	public int numberOfChildren() {
		if (children == null) {
			return 0;
		}
		return children.size();
	}

	/**
	 * Gets a child at a given index.
	 * 
	 * @param index
	 *            index at which we want to get the object
	 * @return child node at given index
	 * @throws IndexOutOfBoundsException
	 *             if the index is less than 0 or greater that number of
	 *             children
	 */
	public Node getChild(int index) {
		if (children == null) {
			return null;
		}
		if (index < 0 || index >= this.numberOfChildren()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		return (Node) children.get(index);
	}

	/**
	 * Returns an empty string.
	 * 
	 * @return an empty string.
	 */
	public String asText() {
		return "";
	}
	
}
