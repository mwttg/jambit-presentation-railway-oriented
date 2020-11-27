package com.jambit.object.oriented2;

import com.jambit.object.oriented2.rest.StudentRequestService;
import com.jambit.object.oriented2.transformation.StudentTransformationService;
import com.jambit.object.oriented2.validation.StudentValidationService;
import com.jambit.shared.StudentFM;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class StudentService {

    private final RestTemplate myRestTemplate;
    private final StudentRequestService studentRequestService;
    private final StudentValidationService studentValidationService;
    private final StudentTransformationService studentTransformationService;

    @SuppressWarnings("ConstantConditions")
    public List<StudentFM> execute() {
        final var response = studentRequestService.execute(myRestTemplate);
        final var validItems = studentValidationService.validateItems(response.getBody());
        return studentTransformationService.convertItems(validItems);
    }
}
