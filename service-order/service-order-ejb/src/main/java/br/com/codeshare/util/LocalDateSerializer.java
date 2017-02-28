package br.com.codeshare.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by mcqueide on 28/02/17.
 */
public class LocalDateSerializer extends StdSerializer<LocalDate> {

    public LocalDateSerializer(){
        super(LocalDate.class);
    }

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        long date = Date.valueOf(localDate).getTime();
        jsonGenerator.writeString(new Long(date).toString());
    }
}
