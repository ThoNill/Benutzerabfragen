package felder;

public class AusgabeFeld extends Feld {
	
	private static final long serialVersionUID = 1280839255181686254L;
	private boolean group;


	public AusgabeFeld(String fieldDefinition,
			boolean group) {
		super(fieldDefinition);
		this.group = group;
	}

	public AusgabeFeld(String tabellenName,
			String fieldDefinition, boolean group) {
		this(fieldDefinition, group);
		put(" t.", tabellenName);
	}

	public boolean isGroup() {
		return group;
	}
}
