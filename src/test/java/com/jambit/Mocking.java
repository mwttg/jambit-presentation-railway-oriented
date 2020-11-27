package com.jambit;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;

public abstract class Mocking {

    @BeforeMethod
    public void initializeMocks() {
        MockitoAnnotations.initMocks(this);
    }
}
