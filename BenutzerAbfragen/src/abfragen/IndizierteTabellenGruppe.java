package abfragen;

public class IndizierteTabellenGruppe extends TabellenGruppe {

	int position = 1;

	public IndizierteTabellenGruppe() {
		super();
	}

	public void put(Tabelle value) {
		value.setPosition(position);
		put(value.getName(), value);
		position++;
	}

	public boolean contains(Tabelle t) {
		return getTabellen().containsKey(t.getName());
	}

}