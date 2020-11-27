package com.jambit.object.oriented1;

import com.jambit.shared.Student;
import com.jambit.shared.StudentFM;
import java.net.URI;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentService extends Service<Void, Student, StudentFM> {

    @Override
    protected RequestEntity<Void> createRequestEntity() {
        final var uri = URI.create("http://www.my-awesome-server.de/student");
        return RequestEntity.get(uri).build();
    }

    @Override
    protected ParameterizedTypeReference<List<Student>> createResponseType() {
        return new ParameterizedTypeReference<>() {
        };
    }

    @Override
    protected boolean isValid(final Student item) {
        return item.getFirstName() != null && !item.getFirstName().isBlank() &&
                item.getLastName() != null && !item.getLastName().isBlank();
    }

    @Override
    protected StudentFM toFrontendModel(final Student item) {
        return new StudentFM(
                item.getFirstName(),
                item.getLastName());
    }
}
