package com.jambit.functional.future;

import io.vavr.API;
import io.vavr.Tuple;
import io.vavr.concurrent.Future;
import org.testng.annotations.Test;

public class Future1Test {

    @Test
    public void testFeature() {
        final var future1 = Future.of(() -> myFunc("error"));
        final var future2 = Future.of(() -> myFunc2("two"));

        final var x = API.For(future1, future2)
                .yield(Tuple::of);

        final var result = x.toEither("error");
        System.out.println(result);
    }

    private String myFunc2(final String input) {
        if (input.equalsIgnoreCase("error")) {
            System.out.println("myFunc1 - error");
            throw new RuntimeException("something went wrong");
        }
        System.out.println("myFunc2 - done");
        return input + "-ok";
    }

    private String myFunc(final String input) throws InterruptedException {
        Thread.sleep(5000);
        if (input.equalsIgnoreCase("error")) {
            System.out.println("myFunc1 - error");
            throw new RuntimeException("something went wrong");
        }
        System.out.println("myFunc1 - done");
        return input + "-ok";
    }
}
