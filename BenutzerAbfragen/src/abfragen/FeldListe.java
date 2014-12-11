package abfragen;

import java.util.Vector;

public class FeldListe<K extends Feld> {
	protected Vector<K> felder = new Vector<>();

	public FeldListe() {

	}

	public boolean add(K e) {
		return felder.add(e);
	}

	public TabellenNamenListe passendeTabellen() {
		TabellenNamenListe erg = new TabellenNamenListe();
		for (Feld v : felder) {
			v.erweitern(erg);
		}
		return erg;
	}

	public String getSelectText(IndizierteTabellenGruppe gruppe) {
		StringBuilder builder = new StringBuilder();
		boolean komma = false;
		builder.append("select ");
		for (Feld feld : felder) {
			komma = kommaDazu(builder, komma);
			builder.append(feld.getField(gruppe));
		}
		return builder.toString();
	}
	
	protected boolean kommaDazu(StringBuilder builder, boolean komma) {
		if (komma) {
			builder.append(", ");
		} else {
			komma = true;
		}
		return komma;
	}

	
}
