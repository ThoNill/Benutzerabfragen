package abfragen;

public class Feld extends TabellenNamenHash {

	private static final long serialVersionUID = -2395899716469986716L;
	private String userDescription;
	private String fieldDefinition;

	public Feld(String userDescription, String fieldDefinition) {
		super();
		this.userDescription = userDescription;
		this.fieldDefinition = fieldDefinition;
	}

	public String getField(IndizierteTabellenGruppe gruppe) {
		return getText(gruppe, fieldDefinition);
	}

	public String getUserDescription() {
		return userDescription;
	}
}
