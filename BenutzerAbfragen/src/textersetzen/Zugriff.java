package textersetzen;

import java.util.HashMap;


public abstract class Zugriff<K> extends ReplaceIt<K> {
	public abstract String getText(K obj);
	
	public String replace(HashMap<String, K> map, String source) {
	   return replace(map, source,this);
	}
	
	public String concat(String prefix, K... objekte) {
		return concat(prefix,this, objekte);
	}
	
	public String concat(String prefix, Iterable<K> Kobjects) {
		return concat(prefix, this,Kobjects);
	}
}
