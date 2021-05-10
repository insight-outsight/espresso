package org.ootb.espresso.facilities.enums;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

public class GeneralEnumDeserializer extends JsonDeserializer<GeneralEnumInterface> {
    
    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public GeneralEnumInterface deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

        logger.debug("field name to be deserialized:{}", jp.currentName());
        logger.debug("field value to be deserialized:{}", jp.getIntValue());
        
        JsonNode node = jp.getCodec().readTree(jp);
//      int id = (Integer) ((IntNode) node.get("id")).numberValue();  
//      String itemName = node.get("itemName").asText();  
//      int userId = (Integer) ((IntNode) node.get("id")).numberValue(); 
        String currentName = jp.currentName();
        Object currentValue = jp.getCurrentValue();
        Class findPropertyType = BeanUtils.findPropertyType(currentName, currentValue.getClass());
        String asText = node.asText();
        GeneralEnumInterface valueOf = null;
        if(!StringUtils.isEmpty(asText)){
            valueOf = (GeneralEnumInterface) GeneralEnumInterface.fromCode(findPropertyType, Integer.valueOf(asText));
        }
        return valueOf;
    }
    
}