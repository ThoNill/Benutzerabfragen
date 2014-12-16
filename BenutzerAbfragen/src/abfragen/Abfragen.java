package abfragen;

import java.util.HashMap;

import felder.AusgabeFeldListe;

public interface Abfragen {
	String createSqlStatement(AusgabeFeldListe felder,HashMap<String,String> parameter);
}
