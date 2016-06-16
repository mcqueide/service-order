/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.codeshare.util;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.codeshare.qualifiers.ApplicationMap;
import br.com.codeshare.qualifiers.RequestMap;
import br.com.codeshare.qualifiers.RequestParameterMap;
import br.com.codeshare.qualifiers.SessionMap;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence
 * context, to CDI beans
 * 
 * <p>
 * Example injection on a managed bean field:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 */
public class WebResources {

	@Produces
	@RequestScoped
	public ExternalContext producesExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	@Produces
	@RequestScoped
	public FacesContext produceFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	@Produces
	public Locale producesLocale() {
		return FacesContext.getCurrentInstance().getViewRoot().getLocale();
	}

	@Produces
	@ApplicationMap
	public Map<String, Object> disponibilizaApplicationMap(ExternalContext ec) {
		return ec.getApplicationMap();
	}

	@Produces
	@SessionMap
	@RequestScoped
	public Map<String, Object> disponibilizaSessionMap(ExternalContext ec) {
		return ec.getSessionMap();
	}

	@Produces
	@RequestMap
	public Map<String, Object> disponibilizaRequestMap(ExternalContext ec) {
		return ec.getRequestMap();
	}

	@Produces
	@RequestParameterMap
	public Map<String, String> disponibilizaParameterMap(ExternalContext ec) {
		return ec.getRequestParameterMap();
	}

	public static String getMessage(String key) {
		ResourceBundle resourceBundle = FacesContext.getCurrentInstance().getApplication()
				.getResourceBundle(FacesContext.getCurrentInstance(), "msg");
		return resourceBundle.getString(key);
	}

}
