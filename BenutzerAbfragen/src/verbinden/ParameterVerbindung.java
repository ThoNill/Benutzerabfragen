package verbinden;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import tabellen.IndizierteTabellenGruppe;
import tabellen.Tabelle;
import tabellen.TabellenNamenListe;
import textersetzen.Zugriff;

public class ParameterVerbindung extends Verbindung {
	HashMap<String,String> parameterNamen = new HashMap<String,String>();

	public ParameterVerbindung(String whereText) {
		super(whereText);
	}

	public ParameterVerbindung(String tabelle,String feldName) {
		super(" a." + feldName + " = ${" +  feldName + "}");
		putZwischen(" a\\.",tabelle);
		putParameter("\\$\\{" + feldName + "\\}", feldName);
	}
	
	
	public void putParameter(String key, String value) {
		parameterNamen.put(key, value);
	}

	public boolean passt(TabellenNamenListe liste) {
		return false;
	}
	
	public boolean passtParameter(TabellenNamenListe liste,List<String> vorhandeneParameter) {
		
		boolean erg = super.passt(liste);
		for (String t : parameterNamen.values()) {
			erg = erg && vorhandeneParameter.contains(t);
		}
		return erg;
	}
	
	public String getWhereText(IndizierteTabellenGruppe gruppe,HashMap<String,String> vorhandeneParameter) {
		String text =  super.getWhereText(gruppe);
		return getParameterText(vorhandeneParameter, text);
	}

	private String getParameterText( HashMap<String, String> vorhandeneParameter, String text) {
		final HashMap<String,String> v = vorhandeneParameter;
		
		Zugriff<String> zugriff = new Zugriff<String>() {
			
			@Override
			public String getText(String name) {
				return v.get(name);
			}
		};
		return zugriff.replace(parameterNamen, text);
	}

}
