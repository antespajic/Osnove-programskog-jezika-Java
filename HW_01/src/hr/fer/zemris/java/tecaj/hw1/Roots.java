package hr.fer.zemris.java.tecaj.hw1;

/**
 * Program koji računa korijene kompleksnog broja
 * 
 * @author Ante Spajic
 * @version 1.0
 */
public class Roots {

	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * 
	 * @param args
	 *            argumenti po redu: realni dio, imaginarni dio, n (za vadenje
	 *            n-tog korijena)
	 */
	public static void main(String[] args) {
		int re; // realni dio
		int im; // imaginarni dio
		int n; // n-ti korijen

		long a, b; // realni i imaginarni dio n-tog korijena
		if (args.length != 3) {
			System.err.println("Invalid number of arguments");
			return;
		} else if (Integer.parseInt(args[2]) < 2) {
			System.err.println("Root must be positive number > 1");
			return;
		}
		re = Integer.parseInt(args[0]);
		im = Integer.parseInt(args[1]);
		n = Integer.parseInt(args[2]);

		double root = Math.sqrt(re * re + im * im);
		root = Math.pow(root, 1. / n);
		double fi = Math.atan(1. * im / re);

		System.out.println("You requested calculation of " + n
				+ ". roots. Solutions are:");
		for (int i = 0; i < n; i++) {

			a = Math.round(root * Math.cos((fi + 2 * i * Math.PI) / n));
			b = Math.round(root * Math.sin((fi + 2 * i * Math.PI) / n));

			if (b > 0)
				System.out.println(i + 1 + ") " + a + " + " + b + "i");
			else
				System.out.println(i + 1 + ") " + a + " - " + (-b) + "i");
		}
	}
}
