package abfragen;

import felder.AusgabeFeld;

public interface  FeldFabrik {
	AusgabeFeld createFeld();
	String getUserDescription();
	String getName();
	String getType();
	boolean isOn();
}
