package org.ootb.espresso.springcloud.infrastructure.enums;

import com.google.common.collect.Maps;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Map;

public class StringCodeToEnumConverterFactory implements ConverterFactory<String, GeneralEnumInterface> {

    private static final Map<Class, Converter> CONVERTERS = Maps.newConcurrentMap();

    @Override
    public <T extends GeneralEnumInterface> Converter<String, T> getConverter(Class<T> targetType) {
        Converter<String, T> converter = CONVERTERS.get(targetType);
        if (converter == null) {
            converter = new StringCodeToEnumConverter<>(targetType);
            Converter previousConverter = CONVERTERS.putIfAbsent(targetType, converter);
            if (previousConverter != null) {
                return previousConverter;
            }
        }
        return converter;
    }

}