package hr.fer.zemris.java.custom.scripting.parser;

import hr.fer.zemris.java.custom.collections.EmptyStackException;
import hr.fer.zemris.java.custom.collections.ObjectStack;
import hr.fer.zemris.java.custom.scripting.elems.Element;
import hr.fer.zemris.java.custom.scripting.elems.ElementVariable;
import hr.fer.zemris.java.custom.scripting.lexer.ScriptToken;
import hr.fer.zemris.java.custom.scripting.lexer.SmartScriptLexer;
import hr.fer.zemris.java.custom.scripting.lexer.TokenType;
import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.nodes.EchoNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.Node;
import hr.fer.zemris.java.custom.scripting.nodes.TextNode;

/**
 * This class represents an object that parses the given document text with
 * according {@link SmartScriptLexer} rules and stores the parsed text inside
 * {@link DocumentNode} object.
 *
 * @author Ante Spajic
 *
 */
public class SmartScriptParser {

	/**
	 * Base node, node of the document that syntax tree is built upon.
	 */
	private DocumentNode documentNode;

	/**
	 * Lexer that does lexical analysis of the document and provides the parser
	 * with tokens needed for correct build of syntax tree document.
	 */
	private SmartScriptLexer lexer;

	/**
	 * Public constructor which accepts text of the document we need to
	 * lexically analyze and starts parsing.
	 * 
	 * @param text
	 *            Text of the document.
	 */
	public SmartScriptParser(String text) {
		if (text == null) {
			throw new IllegalArgumentException("Value given can not be null.");
		}
		lexer = new SmartScriptLexer(text);
		parse();
	}

	/**
	 * Getter for base node of the document that is being parsed
	 * 
	 * @return Base document node.
	 */
	public DocumentNode getDocumentNode() {
		return documentNode;
	}

	/**
	 * The main method used for parsing. Creates a hierarchy of nodes/tags and
	 * stores it in the documentNode collection of children nodes. Parser takes
	 * tokens that lexer generated and uses them to according nodes.
	 * 
	 * @throws SmartScriptParserException
	 *             if an error happens while parsing
	 */
	private void parse() {
		ObjectStack stack = new ObjectStack();
		documentNode = new DocumentNode();
		stack.push(documentNode);

		try {
			ScriptToken token = lexer.nextToken();

			while (token != null && token.getType() != TokenType.EOF) {

				if (token.getType() == TokenType.TAG) {
					ScriptToken tagToken = lexer.nextToken();
					if (tagToken.getType() != TokenType.VARIABLE
							&& !tagToken.getValue().asText().equals("=")) {
						throw new SmartScriptParserException(
								"Tag name is not valid, it is invalid.");
					}

					switch (tagToken.getValue().asText().toLowerCase()) {
					case "for":
						forTag(stack);
						break;
					case "end":
						endTag(stack);
						break;
					default:
						emptyTag(stack);
					}
				} else {
					Node parent = (Node) stack.peek();
					parent.addChildNode(new TextNode(token.getValue().asText()));
				}

				token = lexer.nextToken();
			}
		} catch (Exception e) {
			throw new SmartScriptParserException(
					"An exception happened during parsing " + e.getMessage());
		}

		if (stack.size() != 1) {
			throw new SmartScriptParserException(
					"Document has more opened non-empty tags than END tags.");
		}
	}

	/**
	 * 
	 * Private helper method called if the lexer has generated an opening FOR
	 * tag token. Method checks if FOR syntax is correct and parses it
	 * accordingly else an exception is thrown because of invalid FOR syntax.
	 * Valid FOR syntax is : 1 variable followed by start and end expression and
	 * step expression which can be null as it is not mandatory argument.
	 * 
	 * @param stack
	 *            Stack with parsing elements
	 */
	private void forTag(ObjectStack stack) {
		ScriptToken temp = lexer.nextToken();
		if (temp.getType() != TokenType.VARIABLE) {
			throw new SmartScriptParserException(
					"First argument in FOR tag is not variable name.");
		}

		ObjectStack forStack = new ObjectStack();
		while (temp != null && temp.getType() != TokenType.TAG) {
			switch (temp.getType()) {
			case VARIABLE:
			case CONSTANT_DOUBLE:
			case CONSTANT_INTEGER:
			case STRING:
				forStack.push(temp.getValue());
				break;
			case EOF:
				throw new SmartScriptParserException(
						"FOR tag is not properly closed.");
			default:
				throw new SmartScriptParserException(
						"Invalid arguments in FOR tag.");
			}
			temp = lexer.nextToken();
		}

		ForLoopNode forNode = null;
		Element stepExpression = null;
		Element endExpression = null;
		Element startExpression = null;
		ElementVariable variable = null;

		switch (forStack.size()) {
		case 3:
			endExpression = (Element) forStack.pop();
			startExpression = (Element) forStack.pop();
			variable = (ElementVariable) forStack.pop();
			break;
		case 4:
			stepExpression = (Element) forStack.pop();
			endExpression = (Element) forStack.pop();
			startExpression = (Element) forStack.pop();
			variable = (ElementVariable) forStack.pop();
			break;
		default:
			throw new SmartScriptParserException(
					"Invalid number of arguments in FOR tag.");
		}

		forNode = new ForLoopNode(variable, startExpression, endExpression,
				stepExpression);
		Node parent = (Node) stack.peek();
		parent.addChildNode(forNode);
		stack.push(forNode);

	}

	/**
	 * Method that is called if an empty tag token has been generated. This
	 * method parses an empty tag.
	 * 
	 * @param stack
	 *            Stack with parsing elements
	 */
	private void emptyTag(ObjectStack stack) {

		ObjectStack emptyTagStack = new ObjectStack();
		ScriptToken token = lexer.nextToken();

		while (token != null && token.getType() != TokenType.TAG) {

			switch (token.getType()) {
			case VARIABLE:
			case CONSTANT_DOUBLE:
			case CONSTANT_INTEGER:
			case STRING:
			case FUNCTION:
			case OPERATOR:
				emptyTagStack.push(token.getValue());
				break;
			case EOF:
				throw new SmartScriptParserException(
						"Empty tag is not properly closed.");
			default:
				throw new SmartScriptParserException(
						"Invalid argument in empty tag.");
			}

			token = lexer.nextToken();
		}
		Element[] arguments = new Element[emptyTagStack.size()];
		for (int i = arguments.length - 1; i >= 0; i--) {
			arguments[i] = (Element) emptyTagStack.pop();
		}
		Node parent = (Node) stack.peek();
		parent.addChildNode(new EchoNode(arguments));
	}

	/**
	 * Method that is called if lexer has generated an END tag token. END tag
	 * signalizes that current node on stack has no more children and document
	 * is continued to be parsed out of that tag.
	 * 
	 * @param stack
	 *            Stack with parsing elements
	 */
	private void endTag(ObjectStack stack) {
		try {
			stack.pop();
		} catch (EmptyStackException exception) {
			throw new SmartScriptParserException(
					"Document has more END tags than opened non-empty tags.");
		}

		if (stack.size() == 0) {
			throw new SmartScriptParserException(
					"Document has more END tags than opened non-empty tags.");
		}

		ScriptToken temp = lexer.nextToken();
		if (temp.getType() == TokenType.EOF) {
			throw new SmartScriptParserException(
					"END tag is not properly closed.");
		}
		if (temp.getType() != TokenType.TAG) {
			throw new SmartScriptParserException("Invalid syntax of END tag.");
		}

	}
}
