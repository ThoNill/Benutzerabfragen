package tester;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import abfragen.AusgabeFeld;
import abfragen.AusgabeFeldListe;
import abfragen.IndizierteTabellenGruppe;
import abfragen.ReplaceIt;
import abfragen.Tabelle;
import abfragen.Zugriff;

public class ReplaceItTester {

	
	
	@Test
	public void testReplace() {
		
		test("tabelle.name ${b} "," a.name ${b} ");
		test("tabelle.name wert "," a.name ${a} ");
	
	}
	
	@Test
	public void testConcat() {
		testArray("from a, b ",null,"a","b");
		testArray("",null,null,null,null);
	}

	protected void test(String erg,String source) {
		Zugriff<String> zugriff = new Zugriff<String>() {
			
			public String getText(String s) {
				return s;
			}
		};
		
		
		HashMap<String,String> map = new HashMap<>();
		map.put(" a\\.", "tabelle.");
		map.put("\\$\\{a\\}", "wert");
		
		ReplaceIt<String> r = new ReplaceIt<>();
		
		assertEquals(erg,r.replace(map, source, zugriff));
	}
	
	protected void testArray(String erg,String ... source) {
		Zugriff<String> zugriff = new Zugriff<String>() {
			
			public String getText(String s) {
				return s;
			}
		};
		
		
		ReplaceIt<String> r = new ReplaceIt<>();
		
		assertEquals(erg,r.concat("from",zugriff, source));
	}

}
