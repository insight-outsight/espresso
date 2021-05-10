package org.ootb.espresso.springcloud.infrastructure.enums;

import com.google.common.collect.Maps;

import org.springframework.core.convert.converter.Converter;

import java.util.Map;
import java.util.Objects;

public class StringCodeToEnumConverter<T extends GeneralEnumInterface> implements Converter<String, T> {

    private Map<String, T> enumMap = Maps.newHashMap();
    private Class<T> enumType;

    public StringCodeToEnumConverter(Class<T> enumType) {
        this.enumType = enumType;
        T[] enums = enumType.getEnumConstants();
        for (T e : enums) {
            enumMap.put(String.valueOf(e.getCode()), e);
        }
    }

    @Override
    public T convert(String source) {
        T t = enumMap.get(source);
        if (Objects.isNull(t)) {
            throw new IllegalArgumentException("无法匹配对应的枚举类型, 枚举值：" + source + "，枚举类：" + enumType.getName());
        }
        return t;
    }

}