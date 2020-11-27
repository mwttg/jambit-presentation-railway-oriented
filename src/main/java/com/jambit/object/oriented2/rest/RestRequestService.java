package com.jambit.object.oriented2.rest;

import com.jambit.shared.NoResponseBodyException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("DuplicatedCode")
public abstract class RestRequestService<S, T> {

    public ResponseEntity<T> execute(final RestTemplate restTemplate)
            throws NoResponseBodyException, RestClientException {
        final var responseType = createResponseType();
        final var requestEntity = createRequestEntity();
        final var response = restTemplate.exchange(requestEntity, responseType);

        if (responseType.getType() != Void.class && !response.hasBody()) {
            throw new NoResponseBodyException("missing response body for type " + responseType.toString());
        } else {
            return response;
        }
    }

    protected abstract ParameterizedTypeReference<T> createResponseType();

    protected abstract RequestEntity<S> createRequestEntity();
}
