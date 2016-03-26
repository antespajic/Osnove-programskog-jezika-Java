package hr.fer.zemris.java.tecaj2.demo;

import hr.fer.zemris.java.tecaj2.GeometrijskiLik;

public class Demo1 {

	public static void main(String[] args) {

		GeometrijskiLik g1 = new GeometrijskiLik("lik1");
		GeometrijskiLik g2 = new GeometrijskiLik("lik2");
		
		String ime1 = g1.getIme();
		
		System.out.println("Ime od g1 je: " + ime1);
		System.out.println("Povrsina od g2 je: " + g2.getPovrsina());
	}

}