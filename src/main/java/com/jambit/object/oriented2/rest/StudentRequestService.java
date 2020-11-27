package com.jambit.object.oriented2.rest;

import com.jambit.shared.Student;
import java.net.URI;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentRequestService extends RestRequestService<Void, List<Student>> {

    @Override
    protected ParameterizedTypeReference<List<Student>> createResponseType() {
        return new ParameterizedTypeReference<>() {
        };
    }

    @Override
    protected RequestEntity<Void> createRequestEntity() {
        final var uri = URI.create("http://www.my-awesome-server.de/student");
        return RequestEntity.get(uri).build();
    }
}
