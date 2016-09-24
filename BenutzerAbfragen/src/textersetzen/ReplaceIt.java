package textersetzen;

import java.util.HashMap;

public class ReplaceIt<K> {

	public ReplaceIt() {

	}

	public String replace(HashMap<String, K> map, String sourceParameter,
			Zugriff<K> zugriff) {
	    String source = sourceParameter;
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
	
	public String concat(String prefix, Zugriff<K> zugriff,Iterable<K> Kobjects) {
		StringBuilder builder = new StringBuilder();
		boolean komma = false;
		builder.append(prefix);
		for (K k : Kobjects) {
			String text = zugriff.getText(k);
			if (text != null) {
				komma = kommaDazu(builder, komma);
				builder.append(text);
			}
		}
		return builder.toString();
	}
	
	private boolean kommaDazu(StringBuilder builder, boolean komma) {
		if (komma) {
			builder.append(", ");
		} else {
			return true;
		}
		return komma;
	}

}
