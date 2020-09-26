package com.jambit.functional.monads;

import static org.assertj.core.api.Assertions.assertThat;

import io.vavr.control.Either;
import io.vavr.control.Option;
import org.testng.annotations.Test;


//Monads map/flatMap
public class ExampleMapFlatMapTest {

    @Test
    public void testMap() {
        final var input = Option.of(5);
        final var result = input.map(number -> Either.right(number * 2));

        assertThat(result).isEqualTo(Option.of(10));
    }

    @Test
    public void testFlatMap() {

    }

//    @Test
//    public void testFlatMap() {
//        final var input = Option.of(5);
//        final var result = input.flatMap(i -> Option.of(Either.right(i.doubleValue())));
//
//        assertThat(result).isEqualTo(Option.of(10));
//    }
//
//    @Test
//    public void testFlatMap() {
//        final var input = Either.right(5);
//        //final var result = input.flatMap(i -> i.doubleValue())
//
//
//       // assertThat(result).isEqualTo(Option.of(10));
//    }

}
