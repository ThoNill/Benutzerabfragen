package tabellen;

import java.util.ArrayList;
import java.util.List;

import textersetzen.Zugriff;

public class TabellenNamenListe {
	List<String> namen = new ArrayList<String>();

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
		final IndizierteTabellenGruppe g = gruppe;
		
		Zugriff<String> zugriff = new Zugriff<String>() {

			@Override
			public String getText(String name) {
				return g.get(name).inFrom();
			}
		};
		
		return zugriff.concat("from ", namen);
		
	
	}
}
