package hr.fer.zemris.java.programi;

public class KvalZad2 {

	static class TCvor {
		TCvor lijevi;
		TCvor desni;
		double broj;
	}
	public static void main(String[] args) {
		double[] brojevi = {50,30,80,10,40,90,70};
		TCvor tKorijen = null;
		for(double broj : brojevi) {
			tKorijen = dodajUStablo(tKorijen, broj);
		}
		ispisi(tKorijen);
		System.out.println("Stablo ima cvorova: " + broji(tKorijen));
	}
	
	private static int broji(TCvor tKorijen) {
		if(tKorijen == null){
			return 0;
		}
		return broji(tKorijen.lijevi) + 1 + broji(tKorijen.desni);
	}

	private static void ispisi(TCvor tKorijen) {
		if(tKorijen == null) return;
		ispisi(tKorijen.lijevi);
		System.out.println(tKorijen.broj);
		ispisi(tKorijen.desni);
	}
	
	private static TCvor dodajUStablo(TCvor korijen, double broj) {
		if(korijen == null) {
			TCvor cvor = new TCvor();
			//cvor.lijevi = null;
			//cvor.desni = null;
			cvor.broj = broj;
			return cvor;
		}
		if(broj < korijen.broj) {
			korijen.lijevi = dodajUStablo(korijen.lijevi, broj);
		} else {
			korijen.desni = dodajUStablo(korijen.desni, broj);
		}
		return korijen;
	}

}
