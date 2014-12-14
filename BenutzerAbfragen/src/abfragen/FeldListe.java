package abfragen;

import java.util.Vector;

public class FeldListe<K extends Feld> {
	protected Vector<K> felder = new Vector<>();

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
		Zugriff<Feld> zugriff = new Zugriff<Feld>() {

			@Override
			public String getText(Feld feld) {
				return feld.getField(gruppe);
			}
		};
		return zugriff.concat("select ", zugriff, (Vector<Feld>) felder);

	}

}
