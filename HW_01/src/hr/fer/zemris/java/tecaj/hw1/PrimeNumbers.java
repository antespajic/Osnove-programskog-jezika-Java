package hr.fer.zemris.java.tecaj.hw1;

/**
 * Program koji računa prvih n prostih brojeva.
 * 
 * @author Ante Spajic
 */
public class PrimeNumbers {

	private static boolean isPrime(int n) {
		if (n < 2) {
			return false;
		}
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * 
	 * @param args
	 *            argument komandne linije, broj prostih brojeva koje zelimo
	 *            izracunati
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Invalid number of arguments");
			return;
		}
		int n = Integer.parseInt(args[0]);
		if (n <= 0) {
			System.err.println("Number must be greater than 0");
			return;
		}
		System.out.println("You requested calculation of first " + n
				+ " prime numbers. Here they are: ");
		int counter = 1;
		for (int i = 0; counter != n + 1; ++i) {
			if (isPrime(i)) {
				System.out.println(counter++ + ". " + i);
			}
		}
	}
}
