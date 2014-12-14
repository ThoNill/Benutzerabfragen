package tester;

import static org.junit.Assert.*;

import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

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
		
		felder.add(new AusgabeFeld("kunde","Kunden Nummer", " t.nummer", false));
		felder.add(new AusgabeFeld("kunde","Kunden Name", " t.name", false));
		felder.add(new AusgabeFeld("adresse","Strasse", " t.strasse", true));
		felder.add(new AusgabeFeld("adresse","Ort", " t.ort", false));
		felder.add(new AusgabeFeld("adresse","Plz", " t.plz", false));
		
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
	

}
