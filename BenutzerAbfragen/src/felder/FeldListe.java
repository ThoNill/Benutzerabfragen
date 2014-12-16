package felder;

import java.util.Vector;

import tabellen.IndizierteTabellenGruppe;
import tabellen.TabellenNamenListe;
import textersetzen.Zugriff;

public class FeldListe<K extends Feld> {
	protected Vector<K> felder = new Vector<K>();

	public FeldListe() {

	}

	public boolean add(K e) {
		return felder.add(e);
	}

	public TabellenNamenListe passendeTabellen() {
		TabellenNamenListe erg = new TabellenNamenListe();
		for (Feld v : felder) {
			v.erweitern(erg);
		}
		return erg;
	}

	public String getSelectText(IndizierteTabellenGruppe gruppe) {
		final IndizierteTabellenGruppe g = gruppe;
		
		Zugriff<Feld> zugriff = new Zugriff<Feld>() {
			@Override
			public String getText(Feld feld) {
				return feld.getField(g);
			}
		};
		return zugriff.concat("select ",(Vector<Feld>) felder);

	}

}
