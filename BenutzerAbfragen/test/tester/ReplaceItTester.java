package tester;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Vector;

import org.junit.Test;

import felder.AusgabeFeld;
import felder.AusgabeFeldListe;
import tabellen.IndizierteTabellenGruppe;
import tabellen.Tabelle;
import textersetzen.ReplaceIt;
import textersetzen.Zugriff;

public class ReplaceItTester {

	
	
	@Test
	public void testReplace() {
		
		test("tabelle.name ${b} "," a.name ${b} ");
		test("tabelle.name wert "," a.name ${a} ");
	
	}
	
	@Test
	public void testConcatArray() {
		testArray("from a, b ",null,"a","b");
		testArray("",null,null,null,null);
	}
	
	@Test
	public void testConcatIterable() {
		testIterable("from a, b ",null,"a","b");
		testIterable("",null,null,null,null);
	}

	protected void test(String erg,String source) {
		
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put(" a\\.", "tabelle.");
		map.put("\\$\\{a\\}", "wert");
		
		Zugriff<String> zugriff = new Zugriff<String>() {
			
			public String getText(String s) {
				return s;
			}
		};
		assertEquals(erg,zugriff.replace(map, source, zugriff));
	}
	
	protected void testArray(String erg,String ... source) {
		Zugriff<String> zugriff = new Zugriff<String>() {
			
			public String getText(String s) {
				return s;
			}
		};
		
		assertEquals(erg,zugriff.concat("from",zugriff,source));
	}
	
	protected void testIterable(String erg,String ... source) {
		Vector<String> texte = new Vector<String>();
		
		for(String t : source) {
			texte.add(t);
		}
		
		Zugriff<String> zugriff = new Zugriff<String>() {
			
			public String getText(String s) {
				return s;
			}
		};
		
		assertEquals(erg,zugriff.concat("from",zugriff, source));
	}

}
