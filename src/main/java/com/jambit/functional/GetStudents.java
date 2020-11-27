package com.jambit.functional;

import com.jambit.functional.api.Execute;
import com.jambit.functional.api.HandleError;
import com.jambit.functional.api.Validate;
import com.jambit.shared.Student;
import com.jambit.shared.StudentFM;
import io.vavr.Function1;
import io.vavr.collection.List;
import java.net.URI;
import java.util.function.Predicate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GetStudents {

    public static List<StudentFM> execute(final RestTemplate restTemplate) throws Throwable {
        final var requestEntity = requestEntity();
        final var responseType = responseType();
        final var validationFunction = validStudent();
        final var transformationFunction = transform();

        return Execute
                .synchronous(requestEntity, responseType, restTemplate)
                .map(response -> HandleError.missingBody(response, responseType))
                .map(ResponseEntity::getBody)
                .map(items -> Validate.items(items, validationFunction))
                .map(transformationFunction)
                .getOrElseThrow(Throwable::getCause);
    }

    private static Function1<List<Student>, List<StudentFM>> transform() {
        return items -> items.map(StudentFM::createFrom);
    }

    private static Predicate<Student> validStudent() {
        return item -> item.getFirstName() != null && !item.getFirstName().isBlank() &&
                item.getLastName() != null && !item.getLastName().isBlank() &&
                item.getAddress() != null && !item.getAddress().isBlank();
    }

    private static RequestEntity<Void> requestEntity() {
        final var uri = URI.create("http://www.my-awesome-server.de/student");
        return RequestEntity.get(uri).build();
    }

    private static ParameterizedTypeReference<List<Student>> responseType() {
        return new ParameterizedTypeReference<>() {
        };
    }
}
