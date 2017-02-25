package br.com.codeshare.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by mcqueide on 25/02/17.
 */
@FacesConverter("localDateConverter")
public class LocalDateConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if(value != null && value.trim().length() > 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            formatter.withLocale(new Locale("pt","br"));
            return LocalDate.parse(value,formatter);
        }else{
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        if(object != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            formatter.withLocale(new Locale("pt","br"));

            return ((LocalDate)object).format(formatter);
        }else{
            return null;
        }
    }
}
