package verbinden;

import java.util.Set;
import java.util.Vector;
import java.util.HashMap;

import tabellen.IndizierteTabellenGruppe;
import tabellen.TabellenNamenListe;

public class VerbindungsListe {
	Vector<Verbindung> verbindungen = new Vector<Verbindung>();

	public VerbindungsListe() {

	}

	public boolean add(Verbindung e) {
		return verbindungen.add(e);
	}

	public Vector<Verbindung> passendeVerbindungen(TabellenNamenListe liste,Set<String> vorhandeneParameter) {
		Vector<Verbindung> erg = new Vector<Verbindung>();
		for (Verbindung v : verbindungen) {
			if (v.passt(liste,vorhandeneParameter)) {
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
			Vector<Verbindung> verbindungen,HashMap<String,String> parameter) {
		StringBuilder builder = new StringBuilder();
		builder.append("where 1=1");
		for (Verbindung v : verbindungen) {
			builder.append(" and ");
			builder.append(v.getWhereText(gruppe,parameter));
		}
		return builder.toString();
	}
	
	
	public String getWhereText(IndizierteTabellenGruppe gruppe,TabellenNamenListe tabelleAuswahl,HashMap<String,String> parameter) {
		return getWhereText(gruppe, passendeVerbindungen(tabelleAuswahl,parameter.keySet()),parameter);
	}

	public String getWhereText(IndizierteTabellenGruppe tabellen,
			TabellenNamenListe tabelleAuswahl) {
		return getWhereText(tabellen, tabelleAuswahl, new HashMap<String, String>());
	}

}
