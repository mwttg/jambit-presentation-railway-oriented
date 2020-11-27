package com.jambit;

import org.mockito.MockitoAnnotations;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

public abstract class WithSpringContext extends AbstractTestNGSpringContextTests {

    @BeforeMethod
    public void initializeMocks() {
        MockitoAnnotations.initMocks(this);
    }
}
