package com.jambit.functional.api;

import io.vavr.collection.List;
import java.util.function.Predicate;

public final class Validate {

    private Validate() {
    }

    public static <T> List<T> items(
            final List<T> items,
            final Predicate<T> isValid) {
        return items.filter(isValid);
    }
}
