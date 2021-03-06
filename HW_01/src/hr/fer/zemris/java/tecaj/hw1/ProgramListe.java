package hr.fer.zemris.java.tecaj.hw1;

public class ProgramListe {

	static class CvorListe {
		CvorListe sljedeci;
		String podatak;
	}

	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * 
	 * @param args
	 *            argumenti iz komandne linije
	 */
	public static void main(String[] args) {
		CvorListe cvor = null;
		cvor = ubaci(cvor, "Jasna");
		cvor = ubaci(cvor, "Ana");
		cvor = ubaci(cvor, "Ivana");

		System.out.println("Ispisujem listu uz originalni poredak:");
		ispisiListu(cvor);
		cvor = sortirajListu(cvor);
		System.out.println("Ispisujem listu nakon sortiranja:");
		ispisiListu(cvor);

		int vel = velicinaListe(cvor);
		System.out.println("Lista sadrzi elemenata: " + vel);
	}

	/**
	 * Sortira listu od manjeg elementa prema većem. Za sortiranje je koristen
	 * bubble sort.
	 * 
	 * @param cvor
	 *            Prvi element liste koju zelimo sortirati
	 * @return Prvi element sortirane liste
	 */
	private static CvorListe sortirajListu(CvorListe cvor) {
		if (cvor == null)
			return cvor;

		boolean zamjena = true;

		while (zamjena) {
			zamjena = false;
			for (CvorListe curr = cvor; curr.sljedeci != null; curr = curr.sljedeci) {
				if (curr.podatak.compareTo(curr.sljedeci.podatak) > 0) {
					String tmp = curr.podatak;
					curr.podatak = curr.sljedeci.podatak;
					curr.sljedeci.podatak = tmp;
					zamjena = true;
				}
			}
		}
		return cvor;
	}

	/**
	 * Rekurzivno obilazi listu i broji članove liste.
	 * 
	 * @param cvor
	 *            Prvi element liste koju zelimo prebrojati
	 * @return Broj elemenata u listi
	 */
	private static int velicinaListe(CvorListe cvor) {
		if (cvor == null)
			return 0;
		return 1 + velicinaListe(cvor.sljedeci);
	}

	/**
	 * Ubacuje novi element na kraj liste.
	 * 
	 * @param prvi
	 *            Prvi član liste
	 * @param podatak
	 *            Podatak koji zelimo dodati u listu
	 * @return Prvi element liste
	 */
	private static CvorListe ubaci(CvorListe prvi, String podatak) {
		CvorListe novi = new CvorListe();
		novi.podatak = podatak;

		if (prvi == null)
			return novi;

		CvorListe curr = prvi;
		// pomicemo na kraj liste i onda dodamo element
		while (curr.sljedeci != null)
			curr = curr.sljedeci;

		curr.sljedeci = novi;
		return prvi;
	}

	/**
	 * Rekurzivno obilazi listu i ispisuje podatke spremljene u listi.
	 * 
	 * @param cvor
	 *            Prvi član liste koju zelimo ispisiati
	 */
	private static void ispisiListu(CvorListe cvor) {
		if (cvor == null)
			return;
		System.out.println(cvor.podatak);
		ispisiListu(cvor.sljedeci);
	}
}
