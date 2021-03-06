package hr.fer.zemris.java.tecaj.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Program used to calculate area and perimeter of a rectangle
 * 
 * @author Ante Spajic
 * @version 1.0
 */
public class Rectangle {

    /**
     * Method called when the program is run.
     * 
     * @param args
     *            command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
	double a = 0.0;
	double b = 0.0;

	if (args.length == 2) {
	    a = Double.parseDouble(args[0]);
	    b = Double.parseDouble(args[1]);
	    if (a <= 0.0 || b <= 0.0) {
		System.err.println("Dimension cannot be negative");
		return;
	    }
	} else if (args.length == 0) {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
		    System.in));
	    a = getEdge("width", reader);
	    b = getEdge("height", reader);
	    reader.close();
	} else {
	    System.err.println("Invalid number of arguments, expected number is 2");
	    return;
	}

	double area = 0.0;
	double perimeter = 0.0;
	if (!Double.valueOf(a).equals(0.0) && !Double.valueOf(b).equals(0.0)) {
	    area = a * b;
	    perimeter = 2 * a + 2 * b;
	}

	System.out
		.printf("You have specified a rectangle with width %f and height %f. Its area is %f and its perimeter is %f",
			a, b, area, perimeter);

    }

    /**
     * Returns the dimension value read from standard input.
     * 
     * @param dimension
     *            Dimension you want to enter
     * @param reader
     *            Buffered Reader instance
     * @return positive value read from System.in
     * @throws IOException
     */
    private static double getEdge(String dimension, BufferedReader reader)
	    throws IOException {

	Double value = 0.0;
	while (true) {
	    System.out.println("Please provide " + dimension + ":");
	    String red = reader.readLine();
	    if (red == null) {
		break;
	    }
	    if (red.isEmpty()) {
		System.out.println("Nothing was given");
		continue;
	    }
	    value = Double.parseDouble(red);
	    if (value > 0) {
		break;
	    } 
	    System.out.println(dimension + " is negative");
	}

	return value;
    }

}
