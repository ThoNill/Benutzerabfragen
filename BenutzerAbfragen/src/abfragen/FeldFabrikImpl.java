package abfragen;

import felder.AusgabeFeld;

public class FeldFabrikImpl implements FeldFabrik {

	private String name;
	private String userDescription;
	private boolean on = true;
	private String table;
	private String fieldDescription;
	private String groupFunction;
	private String type;

	public FeldFabrikImpl(String name, String table, String fieldDescription,
			String userDescription,String type) {
		super();
		this.name = name;
		this.table = table;
		this.userDescription = userDescription;
		this.fieldDescription = fieldDescription;
		this.type = type;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public AusgabeFeld createFeld() {
		if (!on)
			return null;

		AusgabeFeld feld = new AusgabeFeld(table, " "
				+ ((groupFunction != null) ? groupFunction + "(" : "")
				+ fieldDescription + ((groupFunction != null) ? ")" : ""),
				groupFunction != null);
		return feld;
	}

	public String getGroupFunction() {
		return groupFunction;
	}

	public void setGroupFunction(String groupFunction) {
		this.groupFunction = groupFunction;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

}