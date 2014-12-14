package abfragen;

import felder.AusgabeFeldListe;

public interface Abfragen {
	String createSqlStatement(AusgabeFeldListe felder);
}
