package hr.fer.zemris.java.hw3;

import hr.fer.zemris.java.custom.scripting.lexer.SmartScriptLexer;
import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.Node;
import hr.fer.zemris.java.custom.scripting.nodes.TextNode;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParser;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Test class for {@link SmartScriptParser} and {@link SmartScriptLexer}. Parses
 * the document and writes it on stdout.
 * 
 * @author Ante Spajic
 *
 */
public class SmartScriptTester {

	/**
	 * Entry point to the program.
	 * 
	 * @param args
	 *            Path to the document to be parsed.
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Invalid number of arugments");
		}

		String filepath = args[0];
		String docBody = null;
		try {
			docBody = new String(Files.readAllBytes(Paths.get(filepath)),
					StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		SmartScriptParser parser = null;
		try {
			parser = new SmartScriptParser(docBody);
		} catch (SmartScriptParserException e) {
			System.out.println("Unable to parse document! \n" + e.getMessage());
			System.exit(-1);
		} catch (Exception e) {
			System.out.println(
					"If this line ever executes, you have failed this class!");
			System.exit(-1);
		}

		DocumentNode document = parser.getDocumentNode();
		String originalBodyDocument = createOriginalDocumentBody(document);
		System.out.println(originalBodyDocument);

	}

	/**
	 * Method that prepares a text document from given {@link DocumentNode}
	 * 
	 * @param document
	 *            DocumentNode with parsed text from another document.
	 * @return String representing {@link DocumentNode} text.
	 */
	public static String createOriginalDocumentBody(Node document) {
		if (document == null) {
			throw new IllegalArgumentException("Value given can not be null.");
		}

		int numberOfChildren = document.numberOfChildren();
		StringBuilder sb = new StringBuilder();
		if (document instanceof TextNode) {
			sb.append(((TextNode) document).getText());
		} else {
			sb.append(document.asText());
		}
		if (numberOfChildren != 0) {
			for (int i = 0; i < numberOfChildren; i++) {
				sb.append(createOriginalDocumentBody(document.getChild(i)));
			}
		}
		if (document instanceof ForLoopNode) {
			sb.append("{$END$}");
		}
		return sb.toString();
	}

}