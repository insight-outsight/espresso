package org.ootb.espresso.springcloud.infrastructure;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GeneralEnumSerializer.class)  
public interface GeneralEnumInterface {

    int getCode();

    String getDescription();

    static <E extends Enum<E> & GeneralEnumInterface> E fromCode(Class<E> clazz, int code) {
        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream().filter(e -> e.getCode() == code).findFirst().orElse(null);
    }
    
    static <E extends Enum<E> & GeneralEnumInterface> E fromDescription(Class<E> clazz, String description) {
        Objects.requireNonNull(description);
        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream().filter(e -> description.equals(e.getDescription())).findFirst().orElse(null);
    }
    

    static <E extends GeneralEnumInterface> Set<Integer> toCodeSet(Set<E> enums) {
        return enums.stream().map(GeneralEnumInterface::getCode).collect(Collectors.toSet());
    }

}
