package tester;

import static org.junit.Assert.*;

import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

import abfragen.AbfregenImpl;
import abfragen.TabellenFeldFabrik;
import felder.AusgabeFeld;
import felder.AusgabeFeldListe;
import tabellen.IndizierteTabellenGruppe;
import tabellen.Tabelle;
import tabellen.TabellenNamenListe;
import textersetzen.ReplaceIt;
import textersetzen.Zugriff;
import verbinden.Verbindung;
import verbinden.VerbindungsListe;

public class AbfrageTester {

	
	@Test
	public void testReplace() {
		
		IndizierteTabellenGruppe tabellen = new IndizierteTabellenGruppe();
		
		tabellen.put(new Tabelle("kunde"));
		tabellen.put(new Tabelle("adresse"));
		tabellen.put(new Tabelle("geschäftsadresse","adresse"));
		
		AusgabeFeldListe felder = new AusgabeFeldListe();
		
		felder.add(new AusgabeFeld("kunde", " t.nummer", false));
		felder.add(new AusgabeFeld("kunde"," t.name", false));
		felder.add(new AusgabeFeld("adresse"," t.strasse", true));
		felder.add(new AusgabeFeld("adresse", " t.ort", false));
		felder.add(new AusgabeFeld("adresse", " t.plz", false));
		
		VerbindungsListe verbindungen = new VerbindungsListe();
		
		verbindungen.add(new Verbindung("kunde","adresse"," a.kundenid = b.kundenid "));
		verbindungen.add(new Verbindung("kunde","geschäftsadresse"," a.kundenid = b.kundenid and b.art = 'G' "));
		
		vergleichen("select  t1.nummer,  t1.name,  t2.strasse,  t2.ort,  t2.plz",felder.getSelectText(tabellen));
		TabellenNamenListe tabelleAuswahl = felder.passendeTabellen();
		
		vergleichen("from kunde t1, adresse t2",tabelleAuswahl.getFromText(tabellen));
		
		vergleichen("where 1=1 and  t1.kundenid = t2.kundenid",verbindungen.getWhereText(tabellen, tabelleAuswahl));
		
		vergleichen("group by 1, 2, 4, 5",felder.getGroupBy());
		
		
	}

	private void vergleichen(String erwartet, String wirklich) {
		assertEquals(standardisieren(erwartet),standardisieren(wirklich));
	}

	private Object standardisieren(String erwartet) {
		return erwartet.trim().replaceAll(" +", " ");
	}
	

	@Test
	public void testAbfragen() {
		
		AbfregenImpl generator = new AbfregenImpl();
		
		generator.add(new Tabelle("kunde"));
		generator.add(new Tabelle("adresse"));
		generator.add(new Tabelle("geschäftsadresse","adresse"));
		
		
		generator.add(new Verbindung("kunde","adresse"," a.kundenid = b.kundenid "));
		generator.add(new Verbindung("kunde","geschäftsadresse"," a.kundenid = b.kundenid and b.art = 'G' "));
		
		AusgabeFeldListe felder = new AusgabeFeldListe();
		
		felder.add(new AusgabeFeld("kunde", " t.nummer", false));
		felder.add(new AusgabeFeld("kunde", " t.name", false));
		felder.add(new AusgabeFeld("adresse", " t.strasse", true));
		felder.add(new AusgabeFeld("adresse"," t.ort", false));
		felder.add(new AusgabeFeld("adresse"," t.plz", false));
		
		vergleichen("select  t1.nummer,  t1.name,  t2.strasse,  t2.ort,  t2.plz from kunde t1, adresse t2 where 1=1 and  t1.kundenid = t2.kundenid group by 1, 2, 4, 5",generator.createSqlStatement(felder));
			
	}
	
	@Test
	public void testAbfragen2() {
		
		AbfregenImpl generator = new AbfregenImpl();
		
		generator.add(new Tabelle("kunde"));
		generator.add(new Tabelle("adresse"));
		generator.add(new Tabelle("geschäftsadresse","adresse"));
		
		
		generator.add(new Verbindung("kunde","adresse"," a.kundenid = b.kundenid "));
		generator.add(new Verbindung("kunde","geschäftsadresse"," a.kundenid = b.kundenid and b.art = 'G' "));
		
		generator.add(new TabellenFeldFabrik("kunde","nummer","Kundennummer"));
		generator.add(new TabellenFeldFabrik("kunde","name","Name"));
		generator.add(new TabellenFeldFabrik("adresse","strasse","Strasse"));
		generator.add(new TabellenFeldFabrik("adresse","ort","Ort"));
		generator.add(new TabellenFeldFabrik("adresse","plz","Plz"));
		
		vergleichen("select  t1.nummer,  t1.name,  t2.strasse,  t2.ort,  t2.plz from kunde t1, adresse t2 where 1=1 and  t1.kundenid = t2.kundenid ",generator.createSqlStatement());
			
	}
	
	@Test
	public void testAbfragen3() {
		
		AbfregenImpl generator = new AbfregenImpl();
		
		generator.add(new Tabelle("kunde"));
		generator.add(new Tabelle("adresse"));
		generator.add(new Tabelle("geschäftsadresse","adresse"));
		generator.add(new Tabelle("rechnung"));
		
		
		generator.add(new Verbindung("kunde","adresse"," a.kundenid = b.kundenid "));
		generator.add(new Verbindung("kunde","geschäftsadresse"," a.kundenid = b.kundenid and b.art = 'G' "));
		generator.add(new Verbindung("kunde","rechnung"," a.kundenid = b.kundenid "));
		
		generator.add(new TabellenFeldFabrik("kunde","nummer","Kundennummer"));
		generator.add(new TabellenFeldFabrik("kunde","name","Name"));
		generator.add(new TabellenFeldFabrik("adresse","strasse","Strasse"));
		generator.add(new TabellenFeldFabrik("adresse","ort","Ort"));
		generator.add(new TabellenFeldFabrik("adresse","plz","Plz"));
		generator.add(new TabellenFeldFabrik("rechnung","soll","Soll"));
		
		generator.setGroupFunction(5, "sum");
				
		vergleichen("select t1.nummer, t1.name, t2.strasse, t2.ort, t2.plz, sum( t4.soll) from kunde t1, adresse t2, rechnung t4 where 1=1 and t1.kundenid = t2.kundenid and t1.kundenid = t4.kundenid group by 1, 2, 3, 4, 5",generator.createSqlStatement());
		
		generator.setOff(2);
		generator.setOff(3);
		generator.setOff(4);
	
		vergleichen("select t1.nummer, t1.name, sum( t4.soll) from kunde t1, rechnung t4 where 1=1 and t1.kundenid = t4.kundenid group by 1, 2",generator.createSqlStatement());
		
	}

}
