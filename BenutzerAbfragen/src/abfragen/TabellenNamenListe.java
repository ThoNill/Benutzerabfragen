package abfragen;

import java.util.Vector;

public class TabellenNamenListe {
	Vector<String> namen = new Vector<>();

	public TabellenNamenListe() {

	}

	public void addFallsNichtDa(String name) {
		if( namen.contains(name)) return;
		
		namen.add(name);
	}

	public boolean contains(String name) {
		return namen.contains(name);
	}

	public String getFromText(IndizierteTabellenGruppe gruppe) {
		
		Zugriff<String> zugriff = new Zugriff<String>() {

			@Override
			public String getText(String name) {
				return gruppe.get(name).inFrom();
			}
		};
		
		return zugriff.concat("from ", zugriff, namen);
		
	
	}
}
