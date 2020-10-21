package org.ootb.espresso.demo.service1.configuration.service;

import java.util.List;

public interface ObjectFormat {

    <T> T fromJson(String json, Class<T> type);

    <T> List<T> fromJsonArray(String json, Class<T> type);

    <T> String toJson(T object);
}
