package org.ootb.espresso.facilities;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
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
    
    /**
     * e.g.
     * Map<String, ResultValue> results = mapper.readValue(jsonSource,new TypeReference<Map<String, ResultValue>>() { } );  
       List<CommitMetaCli> commitMetaClis = mapper.readValue(commitMetasStr, new TypeReference<List<CommitMetaCli>>(){});
       <pre>
        一篇关于TypeReference的博客：http://blog.csdn.net/ssjiang/article/details/7769525
         -----------------article start-----------------
        TypeReference -- 让Jackson Json在List/Map中识别自己的Object
                                                  2012-07-21 09:31
        最近使用了jackson json来格式化数据输出，但是反序列化生成对象的时候碰到点麻烦，jackson把数据默认解
        析成了Map对象，经查询文档，问题解决，在ObjectMapper的readvalue方法中按Object所使用的类型声明即可，代码如下：
        Map<Integer, RbtCounter> srcMap = new LinkedHashMap();
        Map<Integer, RbtCounter> destMap;

        String jsonData = mapper.writeValueAsString(srcMap);

        正确：     
        destMap = mapper.readValue(jsonData, new TypeReference<Map<Integer, RbtCounter>>(){});

        错误
        destMap = mapper.readValue(jsonData, LinkedHashMap.class);

        List中的自定义Object同理解决。
     -----------------article end-----------------
     </pre>
     * @param jsonString
     * @param typeReference
     * @return
     */
    public static <T> T fromJSON(String jsonString, TypeReference<T> typeReference) {

        T object = null;
        try {
            object = objectMapper.readValue(jsonString, typeReference);
        }catch(JsonParseException e){
            throw new RuntimeException("JsonParseException", e);
        }catch (JsonGenerationException e) {
            throw new RuntimeException("JsonGenerationException", e);
        } catch (JsonMappingException e) {
            throw new RuntimeException("JsonMappingException", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
        return object;
    }
    
}
