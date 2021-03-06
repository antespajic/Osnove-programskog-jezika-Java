package hr.fer.zemris.java.custom.collections.demo;

import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * Demonstration program for my implementation of <link>ObjectStack</link>
 * class. This program accepts one command line argument surrounded with
 * quotation marks which is then further evaluated in the program.
 * 
 * @author Ante Spajic
 *
 */
public class StackDemo {

	/**
	 * Entry point to the program Please run this program from command line and
	 * not from eclipse because of eclipse's built in command line argument
	 * parsing is interfering with how program should work, it should treat
	 * argument within quotation marks as 1 not multiple like it does.
	 * 
	 * Example of how to run a program in command line: 
	 * 1. position yourself to project directory 
	 * 2. Open terminal/command line 
	 * 3. run java -cp bin hr.fer.zemris.java.custom.collections.demo.StackDemo "-1 8 2 / +" 
	 * 4.change input arguments inside quotation marks to test the code further.
	 * 
	 * @param args
	 *            1 command line argument which consists of multiple elements
	 *            that need to be split up for this program to work properly.
	 */
	public static void main(String[] args) {

		String[] argumenti = args[0].split(" +");

		ObjectStack stack = new ObjectStack();
		for (int i = 0; i < argumenti.length; i++) {
			if (isInteger(argumenti[i])) {
				stack.push(Integer.parseInt(argumenti[i]));
			} else if (stack.size() >= 2) {
				int first = (int) stack.pop();
				int second = (int) stack.pop();
				try {
					performOperation(first, second, argumenti[i].charAt(0),
							stack);
				} catch (UnsupportedOperationException e) {
					System.err.println("You provided an unsupported operator");
					return;
				} catch (ArithmeticException ex) {
					System.err.println("Division by zero is not allowed");
					return;
				}
			} else {
				System.err
						.println("Your expression is not valid, there must be at least 2 numbers before an operator");
				return;
			}
		}

		if (stack.size() != 1) {
			System.err
					.println("Evaluation error: stack is in unexpected state.");
		} else {
			System.out.println("Expression evaluates to " + stack.pop());
		}
	}

	/**
	 * Helper method that performs a check on given string if it is parseable as
	 * an integer and returns <tt>True</tt> if it is, and <tt>False</tt>
	 * otherwise.
	 * 
	 * @param string
	 *            String to be checked if it is parseable as an integer.
	 * @return True if the string is parseable and false otherwise.
	 */
	private static boolean isInteger(String string) {
		try {
			Integer.parseInt(string);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Method that performs the given operation on top two elements from stack
	 * that are passed as arguments of the function. After determining what
	 * operation should be performed, method performs the action and stores the
	 * result on stack.
	 * 
	 * @param firstOperand
	 *            Top element from stack
	 * @param secondOperand
	 *            Second element from top of the stack
	 * @param operator
	 *            Mathematical operator(supported operators +, -, *, / and %)
	 * @param stack
	 *            Stack to store the result of operation
	 * @throws ArithmeticException
	 *             If user tried to perform division by zero.
	 * 
	 */
	private static void performOperation(int firstOperand, int secondOperand,
			char operator, ObjectStack stack) {
		switch (operator) {
		case '%':
			if (firstOperand == 0) {
				throw new ArithmeticException();
			}
			stack.push(secondOperand % firstOperand);
			break;
		case '/':
			if (firstOperand == 0) {
				throw new ArithmeticException();
			}
			stack.push(secondOperand / firstOperand);
			break;
		case '*':
			stack.push(firstOperand * secondOperand);
			break;
		case '+':
			stack.push(firstOperand + secondOperand);
			break;
		case '-':
			stack.push(secondOperand - firstOperand);
			break;
		default:
			throw new UnsupportedOperationException();
		}

	}
}
