package com.ipartek.formacion.helloweb.i18n;

public enum Idioma {

	CASTELLANO("es_ES", "es", "ES"), EUSKERA("eu_ES", "eu", "ES"), INGLES(
			"en_EN", "en", "EN");

	private String locale;
	private String lenguaje;
	private String pais;

	private Idioma(String locale, String lenguaje, String pais) {
		this.locale = locale;
		this.lenguaje = lenguaje;
		this.pais = pais;
	}

	public String getLocale() {
		return locale;
	}

	public String getLenguaje() {
		return lenguaje;
	}

	public String getPais() {
		return pais;
	}

}
