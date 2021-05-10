package org.ootb.espresso.facilities.enums;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneralEnumSerializer extends JsonSerializer<GeneralEnumInterface> {
    
    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void serialize(GeneralEnumInterface enumValue, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {
//        jsonGenerator.writeObject(enumValue.getCode());
        logger.debug("object to be serialized:{}", enumValue);
//      jgen.writeStartObject();  
//      jgen.writeNumberField("c111", value.getCode());  
//      jgen.writeStringField("d222", value.getDesciption());  
//      jgen.writeEndObject();  
        jsonGenerator.writeNumber(enumValue.getCode());
    }
    
}
