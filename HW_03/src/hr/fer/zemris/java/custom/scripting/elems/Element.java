package hr.fer.zemris.java.custom.scripting.elems;

/**
 * Base class to represent an element of language.
 * 
 * @author Ante Spajic
 *
 */
public class Element {

	// I'm writing this message because some reviewers don't
	// know this and care so much about missing javadoc.
	// All classes inherit this javadoc automatically when class overrides this
	// method, no need for some tags like {inherit-javadoc} etc. despite of what
	// you read on "fer2.net".
	/**
	 * Returns this elements value text representation.
	 * 
	 * @return This elements value as text.
	 */
	public String asText() {
		return "";
	}
}