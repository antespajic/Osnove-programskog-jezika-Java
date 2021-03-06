package hr.fer.zemris.java.tecaj.hw3.prob1;

/**
 * Tokens for language processor, consisting of TokenType and its value.
 * 
 * @author Ante Spajic
 *
 */
public class Token {

	/**
	 * This Token's type descriptor
	 */
	private TokenType type;
	/**
	 * Token's value
	 */
	private Object value;

	/**
	 * Default constructor for Token
	 * 
	 * @param type
	 *            New token's type
	 * @param value
	 *            New token's value
	 */
	public Token(TokenType type, Object value) {
		this.type = type;
		this.value = value;
	}

	/**
	 * Returns this token's value.
	 * 
	 * @return Value of this token.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Returns this token's type.
	 * 
	 * @return Type of this token.
	 */
	public TokenType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "(" + type + ", " + value + ")";
	}
}
