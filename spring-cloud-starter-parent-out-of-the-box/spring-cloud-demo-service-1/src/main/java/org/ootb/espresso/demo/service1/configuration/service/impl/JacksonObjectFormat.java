package org.ootb.espresso.demo.service1.configuration.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.List;
import java.util.function.BiFunction;

import org.ootb.espresso.demo.service1.configuration.service.ObjectFormat;

public class JacksonObjectFormat implements ObjectFormat {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BiFunction<String, IOException, Object> exceptionHandler;

    public JacksonObjectFormat() {
        this((cause, e) -> {
            throw new IllegalStateException(cause, e);
        });
    }

    public JacksonObjectFormat(
            BiFunction<String, IOException, Object> exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
        objectMapper.setSerializationInclusion(Include.NON_NULL);
    }

    @Override
    public <T> T fromJson(String json, Class<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            return handleEx("Failed to deserialize json: " + json, e);
        }
    }

    @Override
    public <T> List<T> fromJsonArray(String json, Class<T> type) {
        try {
            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, type);
            return objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            return handleEx("Failed to deserialize json: " + json, e);
        }
    }

    @Override
    public <T> String toJson(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            return handleEx("Failed to serialize json: " + object, e);
        }
    }

    private <T> T handleEx(String cause, IOException e) {
        return (T) exceptionHandler.apply(cause, e);
    }
}
