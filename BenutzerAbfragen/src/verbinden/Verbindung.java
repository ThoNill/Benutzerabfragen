package verbinden;

import java.util.HashMap;
import java.util.Set;

import tabellen.IndizierteTabellenGruppe;
import tabellen.TabellenNamenHash;
import tabellen.TabellenNamenListe;

public class Verbindung extends TabellenNamenHash {
	
	private static final long serialVersionUID = -2190073760449314178L;
	
	private String whereText;
	private HashMap<String, String> dazu = new HashMap<String,String>();

	public Verbindung(String whereText) {
		super();
		this.whereText = whereText;
	}
	
	public Verbindung(String von,String nach,String whereText) {
		this(whereText);
		putZwischen(" a\\.",von);
		putZwischen(" b\\.",nach);
	}

	public void putDazu(String key, String value) {
		dazu.put(key, value);
	}

	public void putZwischen(String key, String value) {
		put(key, value);
	}

	public String getWhereText(IndizierteTabellenGruppe gruppe,HashMap<String,String> parameters) {
		return getText(gruppe, dazu, getText(gruppe, this, whereText));
	}

	public boolean passt(TabellenNamenListe liste,Set<String> vorhandeneParameter) {
		boolean erg = true;
		for (String t : values()) {
			erg = erg && liste.contains(t);
		}
		return erg;
	}

	public void listeErweitern(TabellenNamenListe liste) {
		for (String t : dazu.values()) {
			liste.addFallsNichtDa(t);
		}
	}

}
