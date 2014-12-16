package abfragen;

import java.util.HashMap;
import java.util.List;
import tabellen.IndizierteTabellenGruppe;
import tabellen.Tabelle;
import tabellen.TabellenNamenListe;
import verbinden.Verbindung;
import verbinden.VerbindungsListe;
import felder.AusgabeFeldListe;

public class AbfregenImpl implements Abfragen {
	private IndizierteTabellenGruppe tabellen = new IndizierteTabellenGruppe();
	private VerbindungsListe verbindungen = new VerbindungsListe();
	private FeldFabrikImplListe feldFabriken = new FeldFabrikImplListe();

	public AbfregenImpl() {
	}
	
	public void add(Tabelle value) {
		tabellen.put(value);
	}
		
	public boolean add(Verbindung e) {
		return verbindungen.add(e);
	}
	
	public void add(TabellenFeldFabrik fabrik) {
		feldFabriken.add(fabrik);
	}

	@Override
	public String createSqlStatement(AusgabeFeldListe felder,HashMap<String,String> parameter) {
		TabellenNamenListe tabelleAuswahl = felder.passendeTabellen();
		String select = felder.getSelectText(tabellen);
		String from = tabelleAuswahl.getFromText(tabellen);
		String where =verbindungen.getWhereText(tabellen, tabelleAuswahl,parameter);
		String groupby = felder.getGroupBy();
		return select + " " + from + " " + where + " " +groupby;
	}
	
	public String createSqlStatement(HashMap<String,String> parameter) {
		return createSqlStatement(feldFabriken.createFeldListe(),parameter);
	}
	
	public String createSqlStatement() {
		return createSqlStatement(new HashMap<String, String>());
	}
	
	public String createSqlStatement(AusgabeFeldListe felder) {
		return createSqlStatement(felder,new HashMap<String, String>());
	}

	public void setOn(int index) {
		feldFabriken.setOn(index);
	}

	public void setOff(int index) {
		feldFabriken.setOff(index);
	}

	public void toggle(int index) {
		feldFabriken.toggle(index);
	}

	public void setGroupFunction(int index, String groupFunction) {
		feldFabriken.setGroupFunction(index, groupFunction);
	}
	
	public List<FeldFabrik> getFabriken() {
		return feldFabriken.getFabriken();
	}

}
