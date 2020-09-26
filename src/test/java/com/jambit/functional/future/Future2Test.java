package com.jambit.functional.future;

import io.vavr.API;
import io.vavr.Function1;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.concurrent.Future;
import io.vavr.control.Option;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.testng.annotations.Test;

public class Future2Test {

    @Test
    public void testFeature() {
        final var details = Future.of(() -> getDetail("ok"));
        final var relations = Future.of(() -> getRelation("error-no"));

        final var result = API.For(details, relations)
                .yield(Tuple::of)
                .map(merge())
                .map(fetchImages())
                .toTry()
                .toEither()
                .mapLeft(Throwable::getMessage)
                .map(createResponse());

        System.out.println(result);
    }

    private static Function1<List<Tuple3<Detail, Option<Relation>, Future<Images>>>, List<MappedResult>> createResponse() {
        return items -> items
                .stream()
                .map(MappedResult::createFrom)
                .collect(Collectors.toList());

    }

    private static Function1<List<Tuple2<Detail, Option<Relation>>>, List<Tuple3<Detail, Option<Relation>, Future<Images>>>> fetchImages() {
        return tuples -> tuples
                .stream()
                .map(tuple -> {
                    final var detail = tuple._1();
                    final var images = getImages(detail, "ok");
                    return Tuple.of(detail, tuple._2(), images);
                })
                .collect(Collectors.toList());
    }

    private static Function1<Tuple2<List<Detail>, List<Relation>>, List<Tuple2<Detail, Option<Relation>>>> merge() {
        return tuple -> {
            final var details = tuple._1();
            final var relations = tuple._2();  // ToDo build relation as Map?

            final var merged = details
                    .stream()  // for each detail in list
                    .map(detail -> {
                        final var relation =
                                Option.ofOptional(
                                        relations
                                                .stream()
                                                .filter(item -> item.getId() == detail.getId())
                                                .findFirst());
                        return Tuple.of(detail, relation);
                    })
                    .collect(Collectors.toList());
            return merged;
        };
    }

    private static Future<Images> getImages(
            final Detail detail,
            final String input) {
        if (input.equalsIgnoreCase("error")) {
            System.out.println("getImages function - error - for detail: " + detail);
            throw new RuntimeException("something went wrong");
        }
        System.out.println("getImages function - done - for detail: " + detail);

        final var desktop = Future.of(() -> getImage(detail, "DESKTOP", input));
        final var mobile = Future.of(() -> getImage(detail, "MOBILE", input));

        return API.For(desktop, mobile)
                .yield(Tuple::of)
                .map(tuple -> new Images(tuple._1(), tuple._2()));
    }

    private static Image getImage(
            final Detail detail,
            final String type,
            final String input) {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (detail.getId() == 2) {
            System.out.println("getImage function - error - for type: " + type + " for detail: " + detail);
            throw new RuntimeException("something went wrong");
        }
        System.out.println("getImage function - done - for type: " + type + " for detail: " + detail);
        return new Image(type + "image for detailId = " + detail.getId());
    }

    private static List<Detail> getDetail(final String input) throws InterruptedException {
        Thread.sleep(3000);
        if (input.equalsIgnoreCase("error")) {
            System.out.println("getDetail function - error");
            throw new RuntimeException("something went wrong");
        }
        System.out.println("getDetail function - done");
        return List.of(
                new Detail(1, "detail-1"),
                new Detail(2, "detail-2"),
                new Detail(3, "detail-3"),
                new Detail(4, "detail-4")); // no relation
    }

    private static List<Relation> getRelation(final String input) throws InterruptedException {
        Thread.sleep(3000);
        if (input.equalsIgnoreCase("error")) {
            System.out.println("getRelation function - error");
            throw new RuntimeException("something went wrong");
        }
        System.out.println("getRelation function - done");
        return List.of(
                new Relation(1, "relation-1"),
                new Relation(2, "relation-2"),
                new Relation(3, "relation-3"),
                new Relation(5, "relation-5")); // no detail
    }
}
