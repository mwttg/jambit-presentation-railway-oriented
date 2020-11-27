package com.jambit.shared;

import lombok.Data;

@Data
public class Student {

    private final String firstName;
    private final String lastName;
    private final int age;
    private final String address;
    private final String note;
}
