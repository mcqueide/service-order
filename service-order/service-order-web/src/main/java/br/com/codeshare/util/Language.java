package br.com.codeshare.util;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class Language implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;
	
	private String localeCode;
	
	public String getLocaleCode() {
		return localeCode;
	}
	
	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}
	
	// value change event listener
	public void countryLocaleCodeChanged() {
		facesContext.getViewRoot().setLocale(new Locale(localeCode));
	}
}
