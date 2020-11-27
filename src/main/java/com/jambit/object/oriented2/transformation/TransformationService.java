package com.jambit.object.oriented2.transformation;

import java.util.List;
import java.util.stream.Collectors;

public abstract class TransformationService<S, T> {

    public List<T> convertItems(final List<S> input) {
        return input.stream()
                .map(this::transformation)
                .collect(Collectors.toList());
    }

    protected abstract T transformation(final S item);
}
