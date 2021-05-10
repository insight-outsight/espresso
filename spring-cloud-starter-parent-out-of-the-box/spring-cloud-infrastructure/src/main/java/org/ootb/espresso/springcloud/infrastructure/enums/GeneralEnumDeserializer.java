package org.ootb.espresso.springcloud.infrastructure;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;

public abstract class GeneralEnumDeserializer<E extends Enum<E> & GeneralEnumInterface> extends JsonDeserializer<E> {  
   
    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private Class<E> clazz;
    
    public GeneralEnumDeserializer(Class<E> clazz) {
        this.clazz = clazz;
    }
    
    @Override  
    public E deserialize(JsonParser jp, DeserializationContext ctxt)   
      throws IOException {  
        logger.debug("field name to be deserialized:{}", jp.currentName());
        logger.debug("field value to be deserialized:{}", jp.getIntValue());
        JsonNode node = jp.getCodec().readTree(jp);
//        int id = (Integer) ((IntNode) node.get("id")).numberValue();  
//        String itemName = node.get("itemName").asText();  
//        int userId = (Integer) ((IntNode) node.get("id")).numberValue();  
        return GeneralEnumInterface.fromCode(clazz, node.numberValue().intValue());
    }  
}  