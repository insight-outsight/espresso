package org.ootb.espresso.facilities;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.BeanUtils;

public class BeanPropertiesCopyUtils {
    
    private BeanPropertiesCopyUtils() {
        super();
    }

    public static <S, T> List<T> copyListProperties(List<S> source, Supplier<T> target) {
        List<T> list = new ArrayList<>(source.size());
        for (S s : source) {
            T t = target.get();
            copyProperties(s, t);
            list.add(t);
        }
        return list;
    }
    
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
    
}