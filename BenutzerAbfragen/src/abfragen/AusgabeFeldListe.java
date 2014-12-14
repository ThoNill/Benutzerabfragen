package abfragen;

public class AusgabeFeldListe extends FeldListe<AusgabeFeld> {

	public AusgabeFeldListe() {

	}

	public String getGroupBy() {
		if (hasGroupByField()) {
			return erzeugeGroupBy();
		} else {
			return "";
		}
	}

	private boolean hasGroupByField() {
		for (AusgabeFeld feld : felder) {
			if (feld.isGroup())
				return true;
		}
		return false;
	}


	private String erzeugeGroupBy() {
		
		Zugriff<AusgabeFeld> zugriff = new Zugriff<AusgabeFeld>() {
			int position =0;
			@Override
			public String getText(AusgabeFeld feld) {
				position++;
				if (feld.isGroup()) {
					return null;
				} else {
					return "" + position;
				}
			}
		};
		return zugriff.concat("group by ", zugriff,  felder);
	}



}
