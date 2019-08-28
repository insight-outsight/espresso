package org.ootb.espresso.facilities;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonJSONUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {

        objectMapper.configure(MapperFeature.USE_ANNOTATIONS, true);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        objectMapper.setDateFormat(simpleDateFormat);

        objectMapper.getSerializationConfig().with(simpleDateFormat)
                .with(MapperFeature.USE_ANNOTATIONS)
                .without(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        objectMapper.getDeserializationConfig().with(simpleDateFormat)
                .with(MapperFeature.USE_ANNOTATIONS)
                .without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    }

    public static <T> String toJSON(T t) {
        try {
            String jsonStr = objectMapper.writeValueAsString(t);
            return jsonStr;
        } catch (JsonGenerationException e) {
            throw new RuntimeException("JsonGenerationException", e);
        } catch (JsonMappingException e) {
            throw new RuntimeException("JsonMappingException", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
    }
    
    public static <T> String toJSONPretty(T t) {
        try {
            String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(t);
            return jsonStr;
        } catch (JsonGenerationException e) {
            throw new RuntimeException("JsonGenerationException", e);
        } catch (JsonMappingException e) {
            throw new RuntimeException("JsonMappingException", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
    }
    
    public static <T> T fromJSON(String jsonString, Class<T> clazz) {

        T object = null;
        try {
            object = objectMapper.readValue(jsonString, clazz);
        } catch (JsonParseException e) {
            throw new RuntimeException("JsonParseException", e);
        } catch (JsonGenerationException e) {
            throw new RuntimeException("JsonGenerationException", e);
        } catch (JsonMappingException e) {
            throw new RuntimeException("JsonMappingException", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
        return object;
    }
    
}
