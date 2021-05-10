package org.ootb.espresso.springcloud.infrastructure;

import java.net.UnknownHostException;

import org.ootb.espresso.springcloud.infrastructure.exception.OotbGlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@Import(OotbGlobalExceptionHandler.class)
class InfrastructureConfiguration {

    @ConditionalOnProperty(value = "ootb.identifier.snowflake.enabled", matchIfMissing = true)
    @Bean
    IpBasedSnowflakeIdGenerator ipBasedSnowflakeIdGenerator() throws UnknownHostException {
        return new IpBasedSnowflakeIdGenerator();
    }
//
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer enumCustomizer(){
//        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.serializerByType(GeneralEnumInterface.class, new JsonSerializer<GeneralEnumInterface>() {
//            @Override
//            public void serialize(GeneralEnumInterface value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//                        gen.writeStartObject();
//                        gen.writeNumberField("code",value.getCode());
//                        gen.writeStringField("description",value.getDesciption());
//                        gen.writeEndObject();
//            }
//        }).deserializerByType(GeneralEnumInterface.class, new JsonDeserializer<GeneralEnumInterface>() {
//
//            @Override
//            public GeneralEnumInterface deserialize(JsonParser p, DeserializationContext ctxt)
//                    throws IOException, JsonProcessingException {
//                int value = p.getIntValue();
//                System.out.println("bbbbbbb---" + value);
//                return GenderEnum.FEMALE;
//            }
//            
//        });
//    }

    @ConditionalOnProperty(value = "ootb.web.json.datetime.enabled")
    @Bean
    MappingJackson2HttpMessageConverter localDatetimeHttpMessageConverter(
            @Value("${ootb.web.json.datetime.pattern:yyyy-MM-dd HH:mm:ss}") String pattern,
            Jackson2ObjectMapperBuilder mapperBuilder) {

        ObjectMapper mapper = mapperBuilder.build();
        // ?? LocalDateFormat based on SimpleDateFormat is not thread-safe
        mapper.setDateFormat(new LocalDateFormat(mapper.getDateFormat(), pattern));

        return new MappingJackson2HttpMessageConverter(mapper);
    }

}
