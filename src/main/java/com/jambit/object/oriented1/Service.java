package com.jambit.object.oriented1;

import com.jambit.shared.NoResponseBodyException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("DuplicatedCode")
public abstract class Service<S, T, U> {

    protected abstract RequestEntity<S> createRequestEntity();

    protected abstract ParameterizedTypeReference<List<T>> createResponseType();

    protected abstract boolean isValid(final T item);

    protected abstract U toFrontendModel(final T item);

    @SuppressWarnings("ConstantConditions")
    public List<U> execute(final RestTemplate restTemplate) throws NoResponseBodyException, RestClientException {
        final var response = executeRestRequest(restTemplate);
        final var items = response.getBody();
        final var validItems = validate(items);
        return transformItems(validItems);
    }

    private ResponseEntity<List<T>> executeRestRequest(final RestTemplate restTemplate) {
        final var responseType = createResponseType();
        final var requestEntity = createRequestEntity();
        final var response = restTemplate.exchange(requestEntity, responseType);
        if (responseType.getType() != Void.class && !response.hasBody()) {
            throw new NoResponseBodyException("missing response body for type " + responseType.toString());
        } else {
            return response;
        }
    }

    private List<T> validate(final List<T> items) {
        return items
                .stream()
                .filter(this::isValid)
                .collect(Collectors.toList());
    }

    private List<U> transformItems(final List<T> input) {
        return input.stream()
                .map(this::toFrontendModel)
                .collect(Collectors.toList());
    }
}
