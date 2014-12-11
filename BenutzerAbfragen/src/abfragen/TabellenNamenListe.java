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
		StringBuilder builder = new StringBuilder();
		boolean komma = false;
		builder.append("from ");
		for (String name : namen) {
			if (komma) {
				builder.append(", ");
			} else {
				komma = true;
			}
			builder.append(gruppe.get(name).inFrom());
		}
		return builder.toString();
	}
}
