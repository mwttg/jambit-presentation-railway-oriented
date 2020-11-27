package com.jambit.object.oriented2.validation;

import com.jambit.shared.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentValidationService extends ValidationService<Student> {

    @Override
    protected boolean isValid(final Student item) {
        return item.getFirstName() != null && !item.getFirstName().isBlank() &&
                item.getLastName() != null && !item.getLastName().isBlank() &&
                item.getAddress() != null && !item.getAddress().isBlank();
    }
}
