package abfragen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import felder.AusgabeFeldListe;

public class FeldFabrikImplListe {
	private List<FeldFabrikImpl> fabriken = new ArrayList<FeldFabrikImpl>();
	private HashMap<String,FeldFabrikImpl> fabrikenHash = new HashMap<String,FeldFabrikImpl>();
	
	public FeldFabrikImplListe() {
		
	}
	
	public void add(FeldFabrikImpl fabrik) {
		fabriken.add(fabrik);
		fabrikenHash.put(fabrik.getName(), fabrik);
	}
	
	public List<FeldFabrik> getFabriken() {
		List<FeldFabrik> erg = new ArrayList<FeldFabrik>();
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

	public String createSafeText() {
		int count = 0;
		for(FeldFabrikImpl f : fabriken) {
			if (f.isOn()) {
				count++;
			}
		}
		StringBuilder builder = new StringBuilder();
		builder.append(count);
		for(FeldFabrikImpl f : fabriken) {
			if (f.isOn()) {
				builder.append(' ');
				builder.append(f.getName());
			}
		}
		return builder.toString();
	}

	public AusgabeFeldListe createFeldListeFromSafeText(String felder) {
		AusgabeFeldListe ausgabeFelder = new AusgabeFeldListe();
		String feldnamen[] = felder.split(" +");
		for(int i = 1;i < feldnamen.length;i++) {
			FeldFabrik f = fabrikenHash.get(feldnamen[i]);
			ausgabeFelder.add(f.createFeld());
		}
		return ausgabeFelder;
	}

}
