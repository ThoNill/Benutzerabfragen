package abfragen;

import java.util.List;
import java.util.Vector;

import felder.AusgabeFeldListe;

public class FeldFabrikImplListe {
	private Vector<FeldFabrikImpl> fabriken = new Vector<FeldFabrikImpl>();
	
	public FeldFabrikImplListe() {
		
	}
	
	public void add(FeldFabrikImpl fabrik) {
		fabriken.add(fabrik);
	}
	
	public List<FeldFabrik> getFabriken() {
		Vector<FeldFabrik> erg = new Vector<FeldFabrik>();
		for(FeldFabrikImpl f : fabriken) {
			if (f.isOn()) {
				erg.add(f);
			}
		}
		return erg;
	}

	
	public AusgabeFeldListe createFeldListe() {
		AusgabeFeldListe ausgabeFelder = new AusgabeFeldListe();
		for(FeldFabrikImpl f : fabriken) {
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
		FeldFabrikImpl f = fabriken.get(index);
		f.setOn(!f.isOn());
	}
	
	public void setGroupFunction(int index,String groupFunction) {
		fabriken.get(index).setGroupFunction(groupFunction);
	}


}
