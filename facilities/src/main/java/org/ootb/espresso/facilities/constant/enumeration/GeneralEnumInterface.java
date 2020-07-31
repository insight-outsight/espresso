package org.ootb.espresso.facilities.constant.enumeration;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public interface GeneralEnumInterface {

    int getCode();

    String getDesciption();

    static <E extends Enum<E> & GeneralEnumInterface> E fromCode(Class<E> clazz, int code) {
//        for (E type : clazz.getEnumConstants()) {
//            if (code == type.getCode()) {
//                return type;
//            }
//        }
//        throw new IllegalArgumentException("Invalid enum type supplied");
        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream().filter(e -> e.getCode() == code).findFirst().orElse(null);
    }
    
    static <E extends Enum<E> & GeneralEnumInterface> E fromDescription(Class<E> clazz, String description) {
        Objects.requireNonNull(description);
        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream().filter(e -> description.equals(e.getDesciption())).findFirst().orElse(null);
    }
    

    static <E extends Enum<E> & GeneralEnumInterface> Set<Integer> toCodeSet(Set<E> enums) {
        return enums.stream().map(x -> x.getCode()).collect(Collectors.toSet());
    }
    

}
