package abfragen;

public class TabellenFeldFabrik extends FeldFabrikImpl {
	
	public TabellenFeldFabrik(String table,String field, String userDescription,String type) {
		super(table + "." + field,table," t." + field,userDescription,type);
	}
	
	public TabellenFeldFabrik(String table,String field, String userDescription) {
		super(table + "." + field,table," t." + field,userDescription,"STRING");
	}
}
