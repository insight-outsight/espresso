package org.ootb.espresso.facilities;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
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

public class JacksonJSONUtilsTest {

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
          
          String jsonList = "[{'bean_id':'1','bean_name':'jack'},{'bean_id':'2','bean_name':'rose'}]";
          List<Bean> beanList = JacksonJSONUtils.fromJSON(jsonList, new TypeReference<List<Bean>>() {});
          for(Bean bean:beanList) {
              System.out.println(bean);
          }
      }
    
    private static class Bean {
        
        @JsonProperty("bean_id")
        private String beanId;
        @JsonProperty("bean_name")
        private String beanName;
        
        public String getBeanId() {
            return beanId;
        }
        public void setBeanId(String beanId) {
            this.beanId = beanId;
        }
        public String getBeanName() {
            return beanName;
        }
        public void setBeanName(String beanName) {
            this.beanName = beanName;
        }
        @Override
        public String toString() {
            return "Bean [beanId=" + beanId + ", beanName=" + beanName + "]";
        }
        
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
