package verbinden;

import java.util.Vector;

import tabellen.IndizierteTabellenGruppe;
import tabellen.TabellenNamenListe;

public class VerbindungsListe {
	Vector<Verbindung> verbindungen = new Vector<>();

	public VerbindungsListe() {

	}

	public boolean add(Verbindung e) {
		return verbindungen.add(e);
	}

	public Vector<Verbindung> passendeVerbindungen(TabellenNamenListe liste) {
		Vector<Verbindung> erg = new Vector<>();
		for (Verbindung v : verbindungen) {
			if (v.passt(liste)) {
				erg.add(v);
				v.listeErweitern(liste);
			}
		}
		return erg;
	}

	public void listeErweitern(TabellenNamenListe liste,
			Vector<Verbindung> verbindungen) {
		for (Verbindung v : verbindungen) {
			v.listeErweitern(liste);
		}
	}

	public String getWhereText(IndizierteTabellenGruppe gruppe,
			Vector<Verbindung> verbindungen) {
		StringBuilder builder = new StringBuilder();
		builder.append("where 1=1");
		for (Verbindung v : verbindungen) {
			builder.append(" and ");
			builder.append(v.getWhereText(gruppe));
		}
		return builder.toString();
	}
	
	
	public String getWhereText(IndizierteTabellenGruppe gruppe,TabellenNamenListe tabelleAuswahl) {
		return getWhereText(gruppe, passendeVerbindungen(tabelleAuswahl));
	}

}
