package abfragen;

import java.util.HashMap;

public class ReplaceIt<K> {

	public ReplaceIt() {

	}

	public String replace(HashMap<String, K> map, String source,
			Zugriff<K> zugriff) {
		for (String key : map.keySet()) {
			K obj = map.get(key);
			if (obj != null) {
				source = source.replaceAll(key, zugriff.getText(obj));
			}
		}
		return source;
	}

	public String concat(String prefix, Zugriff<K> zugriff, K... objekte) {
		StringBuilder builder = new StringBuilder();
		boolean mitKomma = false;
		for (K obj : objekte) {
			String t = zugriff.getText(obj);
			if (t != null) {
				if (mitKomma) {
					builder.append(", ");
				} else {
					builder.append(prefix);
					builder.append(' ');
				}
				builder.append(t);
				mitKomma = true;
			}
		}
		if (mitKomma) {
			builder.append(' ');
		}
		return builder.toString();
	}

}
