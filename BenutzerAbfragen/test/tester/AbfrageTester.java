package tester;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import abfragen.AusgabeFeld;
import abfragen.AusgabeFeldListe;
import abfragen.IndizierteTabellenGruppe;
import abfragen.ReplaceIt;
import abfragen.Tabelle;
import abfragen.TabellenNamenListe;
import abfragen.Verbindung;
import abfragen.VerbindungsListe;
import abfragen.Zugriff;

public class AbfrageTester {

	
	@Test
	public void testReplace() {
		
		IndizierteTabellenGruppe tabellen = new IndizierteTabellenGruppe();
		
		tabellen.put(new Tabelle("kunde"));
		tabellen.put(new Tabelle("adresse"));
		tabellen.put(new Tabelle("geschäftsadresse","adresse"));
		
		AusgabeFeldListe felder = new AusgabeFeldListe();
		
		felder.add(new AusgabeFeld("kunde","Kunden Nummer", " t.nummer", false));
		felder.add(new AusgabeFeld("kunde","Kunden Name", " t.name", false));
		felder.add(new AusgabeFeld("adresse","Strasse", " t.strasse", true));
		felder.add(new AusgabeFeld("adresse","Ort", " t.ort", false));
		felder.add(new AusgabeFeld("adresse","Plz", " t.plz", false));
		
		VerbindungsListe verbindungen = new VerbindungsListe();
		
		verbindungen.add(new Verbindung("kunde","adresse"," a.kundenid = b.kundenid "));
		verbindungen.add(new Verbindung("kunde","geschäftsadresse"," a.kundenid = b.kundenid and b.art = 'G' "));
		
		System.out.println(felder.getSelectText(tabellen));
		TabellenNamenListe tabelleAuswahl = felder.passendeTabellen();
		
		System.out.println(tabelleAuswahl.getFromText(tabellen));
		
		System.out.println(verbindungen.getWhereText(tabellen, tabelleAuswahl));
		
		System.out.println(felder.getGroupBy());
		
		
	}
	

}
