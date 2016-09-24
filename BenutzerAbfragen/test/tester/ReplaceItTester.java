package tester;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.junit.Test;

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
			
			@Override
			public String getText(String s) {
				return s;
			}
		};
		assertEquals(erg,zugriff.replace(map, source, zugriff));
	}
	
	protected void testArray(String erg,String ... source) {
		Zugriff<String> zugriff = new Zugriff<String>() {
			
			@Override
			public String getText(String s) {
				return s;
			}
		};
		
		assertEquals(erg,zugriff.concat("from",zugriff,source));
	}
	
	protected void testIterable(String erg,String ... source) {
		List<String> texte = new ArrayList<String>();
		
		for(String t : source) {
			texte.add(t);
		}
		
		Zugriff<String> zugriff = new Zugriff<String>() {
			
			@Override
			public String getText(String s) {
				return s;
			}
		};
		
		assertEquals(erg,zugriff.concat("from",zugriff, source));
	}

}
