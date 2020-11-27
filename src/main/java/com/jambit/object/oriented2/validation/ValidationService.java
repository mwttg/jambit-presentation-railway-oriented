package com.jambit.object.oriented2.validation;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ValidationService<T> {

    public List<T> validateItems(final List<T> input) {
        return input
                .stream()
                .filter(this::isValid)
                .collect(Collectors.toList());
    }

    protected abstract boolean isValid(T item);
}
