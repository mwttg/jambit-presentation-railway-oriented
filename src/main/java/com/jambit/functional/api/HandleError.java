package com.jambit.functional.api;

import com.jambit.shared.NoResponseBodyException;
import io.vavr.collection.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

public final class HandleError {

    private HandleError() {
    }

    public static <T> ResponseEntity<List<T>> missingBody(
            final ResponseEntity<List<T>> response,
            final ParameterizedTypeReference<List<T>> responseType) {
        if (responseType.getType() != Void.class && !response.hasBody()) {
            throw new NoResponseBodyException("missing response body for type " + responseType.toString());
        } else {
            return response;
        }
    }
}
