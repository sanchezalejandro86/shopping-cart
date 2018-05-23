package ar.com.garbarino.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.mongodb.core.convert.LazyLoadingProxy;

import java.io.IOException;

/**
 * Created by alejandro on 23/05/18.
 */
@JsonComponent
public class LazyLoadingSerializer extends JsonSerializer<LazyLoadingProxy> {

    @Override
    public void serialize(LazyLoadingProxy value, JsonGenerator jgen,
                          SerializerProvider provider) throws IOException,
            JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeStringField("id", value.toDBRef().getId().toString());
        jgen.writeEndObject();
    }
}