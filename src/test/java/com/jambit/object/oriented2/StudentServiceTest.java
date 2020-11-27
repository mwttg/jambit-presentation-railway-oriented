package com.jambit.object.oriented2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.jambit.WithSpringContext;
import com.jambit.object.oriented2.rest.StudentRequestService;
import com.jambit.object.oriented2.transformation.StudentTransformationService;
import com.jambit.object.oriented2.validation.StudentValidationService;
import com.jambit.shared.Student;
import com.jambit.shared.StudentFM;
import java.net.URI;
import java.util.List;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SpringBootTest(classes = Application.class)
public class StudentServiceTest extends WithSpringContext {

    @Mock
    private RestTemplate restTemplate;

    @Autowired
    private StudentRequestService studentRequestService;

    @Autowired
    private StudentValidationService studentValidationService;

    @Autowired
    private StudentTransformationService studentTransformationService;

    private StudentService subject;

    private final ParameterizedTypeReference<List<Student>> responseType = new ParameterizedTypeReference<>() {
    };

    private RequestEntity<Void> request;

    @BeforeMethod
    public void setUp() {
        final var uri = URI.create("http://www.my-awesome-server.de/student");
        request = RequestEntity.get(uri).build();

        subject = new StudentService(
                restTemplate,
                studentRequestService,
                studentValidationService,
                studentTransformationService);
    }

    @Test
    public void testGetStudents() {
        final var response = List.of(
                new Student("Hans", "Wurst", 18, "Somewhere 1", "extra info"),
                new Student("Maja", "  ", 19, "Somewhere 1", "extra info"),
                new Student("Peter", "Blub", 18, "Somewhere 1", null));
        when(restTemplate.exchange(request, responseType)).thenReturn(ResponseEntity.ok(response));

        final var actual = subject.execute();
        assertThat(actual).isEqualTo(
                List.of(
                        new StudentFM("Hans", "Wurst"),
                        new StudentFM("Peter", "Blub")));
    }
}
