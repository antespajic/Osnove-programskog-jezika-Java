package hr.fer.zemris.java.custom.scripting.elems;

/**
 * Class that extends Element and is used to store function elements used in
 * echo and forloop nodes.
 * 
 * @author Ante Spajic
 *
 */
public class ElementFunction extends Element {

	private String name;

	/**
	 * Class constructor specifying the name of a function.
	 *
	 * @param name
	 *            name of a function
	 * @throws IllegalArgumentException
	 *             if <code>name</code> is <code>null</code>
	 */
	public ElementFunction(String name) {
		if (name == null) {
			throw new IllegalArgumentException(
					"Can't create a function token from null.");
		}
		this.name = name;
	}

	/**
	 * Returns the name of this function.
	 * 
	 * @return the name of function
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the name of the token preceded by a @ sign.
	 * 
	 * @return the name
	 */
	@Override
	public String asText() {
		return "@" + this.name;
	}
}
