package felder;

import tabellen.IndizierteTabellenGruppe;
import tabellen.TabellenNamenHash;

public class Feld extends TabellenNamenHash {

	private static final long serialVersionUID = -2395899716469986716L;

	private String fieldDefinition;

	public Feld(String fieldDefinition) {
		super();
		this.fieldDefinition = fieldDefinition;
	}

	public String getField(IndizierteTabellenGruppe gruppe) {
		return getText(gruppe, fieldDefinition);
	}

	public String toString() {
		return fieldDefinition;
	}


}
