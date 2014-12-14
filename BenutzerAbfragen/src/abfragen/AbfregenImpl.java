package abfragen;

import tabellen.IndizierteTabellenGruppe;
import tabellen.Tabelle;
import tabellen.TabellenNamenListe;
import verbinden.Verbindung;
import verbinden.VerbindungsListe;
import felder.AusgabeFeldListe;

public class AbfregenImpl implements Abfragen {
	private IndizierteTabellenGruppe tabellen = new IndizierteTabellenGruppe();
	private VerbindungsListe verbindungen = new VerbindungsListe();
	private AusgabeFelFabrikListe ausgabe = new AusgabeFelFabrikListe();

	public AbfregenImpl() {
	}
	
	public void add(Tabelle value) {
		tabellen.put(value);
	}
		
	public boolean add(Verbindung e) {
		return verbindungen.add(e);
	}
	
	public void add(AusgabeFeldFabrikImpl fabrik) {
		ausgabe.add(fabrik);
	}

	@Override
	public String createSqlStatement(AusgabeFeldListe felder) {
		TabellenNamenListe tabelleAuswahl = felder.passendeTabellen();
		String select = felder.getSelectText(tabellen);
		String from = tabelleAuswahl.getFromText(tabellen);
		String where =verbindungen.getWhereText(tabellen, tabelleAuswahl);
		String groupby = felder.getGroupBy();
		return select + " " + from + " " + where + " " +groupby;
	}
	
	public String createSqlStatement() {
		return createSqlStatement(ausgabe.createFeldListe());
	}

	public void setOn(int index) {
		ausgabe.setOn(index);
	}

	public void setOff(int index) {
		ausgabe.setOff(index);
	}

	public void toggle(int index) {
		ausgabe.toggle(index);
	}

	public void setGroupFunction(int index, String groupFunction) {
		ausgabe.setGroupFunction(index, groupFunction);
	}

}
