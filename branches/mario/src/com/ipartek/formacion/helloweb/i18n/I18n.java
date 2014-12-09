package com.ipartek.formacion.helloweb.i18n;

import java.util.Locale;

/**
 * Clase utilidades para los idiomas
 *
 * @author Mario Alvaro
 *
 */
public class I18n {

    /**
     * Obtener el locale(idioma) del navegador para retornar la cadena de locale
     * que nosotros necesitamos para nuestra app. Si no se encuentra devuelve el
     * valor "en_EN" por defecto. <h1>Lista de locales de la App</h1>
     * <ol>
     * <li>es_ES</li>
     * <li>eu_ES</li>
     * <li>en_EN</li>
     * </ol>
     *
     * @return cadena con el locale de nuestra App, por defecto en_EN
     *
     */
    public static final String getBrowserLocale(Locale localeBrowser) {
	// por defecto ingles

	String result = Idioma.INGLES.getLocale();
	// si no es nulo
	if (localeBrowser != null) {
	    // es euskera
	    if (Idioma.EUSKERA.getLenguage().equalsIgnoreCase(
		    localeBrowser.getLanguage())) {

		result = Idioma.EUSKERA.getLocale();
		// es castellano
	    } else if (Idioma.CASTELLANO.getLenguage().equalsIgnoreCase(
		    localeBrowser.getLanguage())) {

		result = Idioma.CASTELLANO.getLocale();
	    }
	}
	return result;
    }
}