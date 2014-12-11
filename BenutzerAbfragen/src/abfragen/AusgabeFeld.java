package abfragen;

public class AusgabeFeld extends Feld {
	
	private static final long serialVersionUID = 1280839255181686254L;
	private boolean group;


	public AusgabeFeld(String userDescription, String fieldDefinition,
			boolean group) {
		super(userDescription, fieldDefinition);
		this.group = group;
	}

	public AusgabeFeld(String tabellenName, String userDescription,
			String fieldDefinition, boolean group) {
		this(userDescription, fieldDefinition, group);
		put(" t.", tabellenName);
	}

	public boolean isGroup() {
		return group;
	}
}
