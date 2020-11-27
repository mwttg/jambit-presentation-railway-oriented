package com.jambit.object.oriented2.transformation;

import com.jambit.shared.Student;
import com.jambit.shared.StudentFM;
import org.springframework.stereotype.Component;

@Component
public class StudentTransformationService extends TransformationService<Student, StudentFM> {

    @Override
    protected StudentFM transformation(final Student student) {
        return StudentFM.createFrom(student);
    }
}
