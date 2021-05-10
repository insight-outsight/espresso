package org.ootb.espresso.facilities.format;

import java.util.List;

public interface ObjectFormat {

    <T> T fromJson(String json, Class<T> type);

    <T> List<T> fromJsonArray(String json, Class<T> type);

    <T> String toJson(T object);

    <T> String toJsonPretty(T t);

}