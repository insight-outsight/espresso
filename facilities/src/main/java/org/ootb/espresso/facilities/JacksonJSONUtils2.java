package org.ootb.espresso.facilities;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonJSONUtils2 {

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
        
        //驼峰和下划线互转
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

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
    
    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
        System.out.println((Long.MAX_VALUE+"").length());
        
          long st = System.currentTimeMillis();
          Counter entity = new Counter();
          entity.setRoutingKey("s_idrrkkkk");
          entity.setSType(33324);
          entity.setBizId(2200043);
          entity.setBizType(101010);
          entity.setStatus(1);
          for (int i = 0; i < 10000; i++) {
              entity.setId(i+"aaaaasdsdfs");
              entity.setOId(System.currentTimeMillis()+i+"sfd");
              entity.setMTime(System.currentTimeMillis());
//              System.out.println(toJSON(entity));
          }
          System.out.println("cost: "+(System.currentTimeMillis()-st));
      }
//    public static void main(String[] args) {
//        long st = System.currentTimeMillis();
//        Map<String,Object> entity = new HashMap();
//        entity.put("routingKey","s_idrrkkkk");
//        entity.put("s_type",33324);
//        entity.put("biz_id",2200043);
//        entity.put("biz_type",101010);
//        entity.put("status",1);
//        for (int i = 0; i < 10000; i++) {
//            entity.put("id",i+"aaaaasdsdfs");
//            entity.put("o_id",System.currentTimeMillis()+i+"sfd");
//            entity.put("m_time",System.currentTimeMillis());
////            System.out.println(toJSON(entity));
//        }
//        System.out.println("cost: "+(System.currentTimeMillis()-st));
//    }
    
}
