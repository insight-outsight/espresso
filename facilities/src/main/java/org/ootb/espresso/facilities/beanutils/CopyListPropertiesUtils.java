package org.ootb.espresso.facilities.beanutils;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;

public class CopyListPropertiesUtils {

    public static <T,S> List<S> copyListProperties(List<T> fromList,Class<S> toObj) {
        Objects.requireNonNull(fromList);
        Objects.requireNonNull(toObj);
        List<S> toList = Lists.newArrayList();
        fromList.stream().forEach(from -> {
            try {
                S to = toObj.newInstance();
                BeanUtils.copyProperties(from, to);
                toList.add(to);
            } catch (Exception e) {
                throw new RuntimeException("copy list Error", e);
            }
        });
        return toList;
    }
    
}
