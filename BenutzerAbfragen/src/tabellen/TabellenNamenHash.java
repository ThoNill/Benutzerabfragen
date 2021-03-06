package tabellen;

import java.util.HashMap;

import textersetzen.Zugriff;

public class TabellenNamenHash extends HashMap<String, String> {

	private static final long serialVersionUID = 5997633508847901602L;

	public TabellenNamenHash() {

	}

	public void erweitern(TabellenNamenListe erg) {
		for (String name : values()) {
			erg.addFallsNichtDa(name);
		}

	}

	public String getText(IndizierteTabellenGruppe gruppe,
			String fieldDefinition) {
		return getText(gruppe, this, fieldDefinition);
	}

	protected String getText(IndizierteTabellenGruppe gruppe,
			HashMap<String, String> map, String text) {
		
		final IndizierteTabellenGruppe g = gruppe;
		
		Zugriff<String> zugriff = new Zugriff<String>() {

			@Override
			public String getText(String name) {
				Tabelle t = g.get(name);
				return " " + t.alias() + ".";
			}
		};
		return zugriff.replace(map, text);
	}
}
