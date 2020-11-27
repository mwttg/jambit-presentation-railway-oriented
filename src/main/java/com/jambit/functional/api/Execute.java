package com.jambit.functional.api;

import io.vavr.collection.List;
import io.vavr.control.Try;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public final class Execute {

    private Execute() {
    }

    public static <S, T> Try<ResponseEntity<List<T>>> synchronous(
            final RequestEntity<S> requestEntity,
            final ParameterizedTypeReference<List<T>> responseType,
            final RestTemplate restTemplate) {
        return Try.of(() -> restTemplate.exchange(requestEntity, responseType));
    }
}
