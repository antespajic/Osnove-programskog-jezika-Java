package hr.fer.zemris.java.tecaj.hw3.prob1;

/**
 * 
 * 
 * @author Ante Spajic
 *
 */
public class Lexer {

	private static final char STATE_CHANGER = '#';

	private char[] data;
	private Token token;
	private int currentIndex;
	private LexerState state;

	public Lexer(String text) {
		if (text == null) {
			throw new IllegalArgumentException("Invalid text argument");
		}
		text = text.trim();
		this.data = text.toCharArray();
		this.currentIndex = 0;
		this.state = LexerState.BASIC;
	}

	/**
	 * Generates a new token from given text and returns it.
	 * 
	 * @return
	 * @throws LexerException
	 */
	public Token nextToken() {
		if (currentIndex > data.length && token.getType().equals(TokenType.EOF)) {
			throw new LexerException("There are no more tokens to be generated");
		}

		if (currentIndex == data.length) {
			currentIndex++;
			token = new Token(TokenType.EOF, null);
		}

		if (currentIndex < data.length) {
			// skip spaces
			while (Character.toString(data[currentIndex]).matches("\\s+")
					&& currentIndex < data.length - 1) {
				currentIndex++;
			}
			
			switch (state) {
			case BASIC:
				token = basicState();
				break;
			case EXTENDED:
				token = extendedState();
				break;
			}
		}
		return token;
	}

	private Token extendedState() {

		TokenType type = null;
		Object value = null;
		StringBuilder sb = new StringBuilder();

		type = data[currentIndex] == STATE_CHANGER ? TokenType.SYMBOL
				: TokenType.WORD;
		for (; currentIndex < data.length; currentIndex++) {
			if (data[currentIndex] == ' ') {
				break;
			}
			if (data[currentIndex] == STATE_CHANGER) {
				setState(LexerState.BASIC);
				break;
			}
			sb.append(data[currentIndex]);
		}

		if (sb.length() > 0) {
			type = TokenType.WORD;
			value = sb.toString();
		} else {
			type = TokenType.SYMBOL;
			value = STATE_CHANGER;
			currentIndex++;
		}

		return new Token(type, value);
	}

	private Token basicState() {
		TokenType type = null;
		Object value = null;

		if (Character.isLetter(data[currentIndex])
				|| data[currentIndex] == '\\') {
			type = TokenType.WORD;
			value = processWord();
		} else if (Character.isDigit(data[currentIndex])) {
			type = TokenType.NUMBER;
			try {
				value = processNumber();
			} catch (NumberFormatException e) {
				throw new LexerException("That number, wow, its too big");
			}
		} else {
			return processSymbol();

		}
		return new Token(type, value);
	}

	private Token processSymbol() {
		String symbol = Character.toString(data[currentIndex++]).replaceAll(
				"[a-zA-Z0-9\\s*]", "");
		symbol.trim();
		if (!symbol.isEmpty()) {
			Object value = symbol.toCharArray()[0];
			if ((char) value == STATE_CHANGER) {
				setState(LexerState.EXTENDED);
			}
			return new Token(TokenType.SYMBOL, value);
		} else {
			return new Token(TokenType.EOF, null);
		}
	}

	private Long processNumber() throws NumberFormatException {
		StringBuilder sb = new StringBuilder();
		for (; currentIndex < data.length; currentIndex++) {
			if (data[currentIndex] == ' '
					|| !Character.isDigit(data[currentIndex])) {
				break;
			}
			sb.append(data[currentIndex]);
		}
		return Long.parseLong(sb.toString());
	}

	private String processWord() {
		StringBuilder sb = new StringBuilder();
		for (; currentIndex < data.length; currentIndex++) {
			if (data[currentIndex] == '\\') {
				if (currentIndex >= data.length - 1) {
					throw new LexerException(
							"Character expected after escape sign.");
				}
				if (!Character.isDigit(data[currentIndex + 1])
						&& data[currentIndex + 1] != '\\') {
					throw new LexerException(
							"Invalid escape sequence, number or backslash is expected.");
				}
				sb.append(data[currentIndex + 1]);
				currentIndex++;
				continue;
			}
			if (data[currentIndex] == ' '
					|| !Character.isLetter(data[currentIndex])) {
				break;
			}
			sb.append(data[currentIndex]);
		}
		return sb.toString();
	}

	/**
	 * Returns last generated token.
	 * 
	 * @return Last generated token.
	 */
	public Token getToken() {
		return token;
	}

	/**
	 * Changes the state of lexer.
	 * 
	 * @param state
	 *            New state to be set.
	 */
	public void setState(LexerState state) {
		if (state == null || !(state instanceof LexerState)) {
			throw new IllegalArgumentException("State cannot be: " + state);
		}
		this.state = state;
	}
}
