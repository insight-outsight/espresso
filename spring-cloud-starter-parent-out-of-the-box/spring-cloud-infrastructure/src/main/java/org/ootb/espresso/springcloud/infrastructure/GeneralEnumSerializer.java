package org.ootb.espresso.springcloud.infrastructure;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class GeneralEnumSerializer extends JsonSerializer<GeneralEnumInterface> {
    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void serialize(GeneralEnumInterface value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        logger.debug("object to be serialized:{}", value);
//        jgen.writeStartObject();  
//        jgen.writeNumberField("c111", value.getCode());  
//        jgen.writeStringField("d222", value.getDesciption());  
//        jgen.writeEndObject();  
        jgen.writeNumber(value.getCode());
    }
}