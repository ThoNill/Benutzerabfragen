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
		StringBuilder builder = new StringBuilder();
		builder.append("group by ");
		boolean komma = false;
		int position = 1;
		for (AusgabeFeld feld : felder) {
			if (!feld.isGroup()) {
				komma = kommaDazu(builder, komma);
				builder.append(position);
			}
			position++;
		}
		return builder.toString();
	}



}
