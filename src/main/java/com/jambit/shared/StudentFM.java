package com.jambit.shared;

import lombok.Data;

@Data
public class StudentFM {

    private final String firstName;
    private final String lastName;

    public static StudentFM createFrom(final Student student) {
        return new StudentFM(
                student.getFirstName(),
                student.getLastName());
    }
}
