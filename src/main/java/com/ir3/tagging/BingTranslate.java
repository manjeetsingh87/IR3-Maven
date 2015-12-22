package com.ir3.tagging;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class BingTranslate {

	public static String translate(String query, String lang)
	{
		String queryTranslated = null;

		Translate.setClientId("IR_Project");
		Translate.setClientSecret("oMUBNcizYcBnaonCrFZVm32O/uML2vZeLBIEtHxkYQA=");
		
		if(lang == "ru")
		{
			try {
				queryTranslated = Translate.execute(query, Language.RUSSIAN, Language.ENGLISH);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(lang == "de")
		{
			try {
				queryTranslated = Translate.execute(query, Language.GERMAN, Language.ENGLISH);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(lang == "fr")
		{
			try {
				queryTranslated = Translate.execute(query, Language.FRENCH, Language.ENGLISH);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			queryTranslated = query;
		}
		
		return queryTranslated;		
	}
}
