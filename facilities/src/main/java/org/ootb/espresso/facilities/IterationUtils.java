package org.ootb.espresso.facilities;

import java.util.Objects;
import java.util.function.BiConsumer;

public class IterationUtils {

    private IterationUtils() {
        super();
    }

    public static <E> void forEachWithElementIndex(
            Iterable<? extends E> elements, BiConsumer<Integer, ? super E> action) {
        Objects.requireNonNull(elements);
        Objects.requireNonNull(action);

        int index = 0;
        for (E element : elements) {
            action.accept(index++, element);
        }
    }
    
}