package abfragen;

public class Tabelle {
	private String name;
	private String tableName;
	private int position;

	public Tabelle(String name,String tableName) {
		super();
		this.name = name;
		this.tableName = tableName;
	}
	
	public Tabelle(String name) {
		this(name,name);
	}

	public String getName() {
		return name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String inFrom() {
		return tableName + " " + alias();
	}

	public String alias() {
		return "t" + position;
	}

}
