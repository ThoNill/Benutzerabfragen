package abfragen;

public abstract class Zugriff<K> extends ReplaceIt<K> {
	public abstract String getText(K obj);
}
