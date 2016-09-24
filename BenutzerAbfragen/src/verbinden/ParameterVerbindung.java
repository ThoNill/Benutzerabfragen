package verbinden;

import java.util.HashMap;
import java.util.Set;

import tabellen.IndizierteTabellenGruppe;
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

	@Override
	public boolean passt(TabellenNamenListe liste,Set<String> vorhandeneParameter) {
		boolean erg = super.passt(liste,vorhandeneParameter);
		for (String t : parameterNamen.values()) {
			erg = erg && vorhandeneParameter.contains(t);
		}
		return erg;
	}
	
	@Override
	public String getWhereText(IndizierteTabellenGruppe gruppe,HashMap<String,String> parameter) {
		String text =  super.getWhereText(gruppe,parameter);
		return getParameterText(parameter, text);
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
