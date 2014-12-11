package abfragen;

import java.util.HashMap;

public class TabellenGruppe {

	protected HashMap<String, Tabelle> tabellen = new HashMap<>();

	public TabellenGruppe() {
		super();
	}

	public HashMap<String, Tabelle> getTabellen() {
		return tabellen;
	}

	public void put(String key, Tabelle value) {
		tabellen.put(key, value);
	}

	public Tabelle get(String key) {
		return tabellen.get(key);
	}

}