package abfragen;

import java.util.Vector;

import felder.AusgabeFeldListe;

public class AusgabeFelFabrikListe {
	private Vector<AusgabeFeldFabrikImpl> fabriken = new Vector<>();
	
	public AusgabeFelFabrikListe() {
		
	}
	
	public void add(AusgabeFeldFabrikImpl fabrik) {
		fabriken.add(fabrik);
	}
	
	public AusgabeFeldListe createFeldListe() {
		AusgabeFeldListe ausgabeFelder = new AusgabeFeldListe();
		for(AusgabeFeldFabrikImpl f : fabriken) {
			if (f.isOn()) {
				ausgabeFelder.add(f.createFeld());
			}
		}
		return ausgabeFelder;
	}
	
	public void setOn(int index) {
		fabriken.get(index).setOn(true);
	}
	
	public void setOff(int index) {
		fabriken.get(index).setOn(false);
	}
	
	public void toggle(int index) {
		AusgabeFeldFabrikImpl f = fabriken.get(index);
		f.setOn(!f.isOn());
	}
	
	public void setGroupFunction(int index,String groupFunction) {
		fabriken.get(index).setGroupFunction(groupFunction);
	}


}
