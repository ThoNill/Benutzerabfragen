package abfragen;

import felder.AusgabeFeld;

public class AusgabeFeldFabrikImpl implements AusgabeFeldFabrik {
	private String userDescription;
	private String field;
	private String table;
	private String groupFunction;
	private boolean on = true;

	public AusgabeFeldFabrikImpl(String table,String field, String userDescription) {
		super();
		this.userDescription = userDescription;
		this.field = field;
		this.table = table;
		this.on = true;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public AusgabeFeld createFeld() {
		if (!on) return null;
		
		AusgabeFeld feld = new AusgabeFeld(table," "
				+ ((groupFunction != null) ? groupFunction + "( t." : " t.") + field
				+ ((groupFunction != null) ? ")" : ""), groupFunction != null);
		feld.setUserDescription(userDescription);
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

}
