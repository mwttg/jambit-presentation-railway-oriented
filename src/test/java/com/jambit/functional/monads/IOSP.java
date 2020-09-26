package com.jambit.functional.monads;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.Test;

public class IOSP {

    @Test
    public void myMethod() {
        final var data = loadData();

        var modifiedData = new ArrayList<String>();
        for (final String item : data) {
            final var newItem = "PREFIX-" + item;
            modifiedData.add(newItem);
        }

        var againModifiedData = new ArrayList<String>();
        for (final String item : modifiedData) {
            final var newItem = item + "-POSTFIX";
            againModifiedData.add(newItem);
        }

        printData(againModifiedData);
    }

    @Test
    public void myMethod2() {
        final var data = loadData();
        final var dataWithPrefix = addPrefix(data);
        final var dataWithPrefixAndPostfix = addPostfix(dataWithPrefix);
        printData(dataWithPrefixAndPostfix);
    }

    private static List<String> loadData() {
        return List.of("my", "awesome", "data");
    }

    private static List<String> addPrefix(final List<String> data) {
        return data.stream().map(item -> "PREFIX-" + item).collect(Collectors.toList());
    }
    private static List<String> addPostfix(final List<String> data) {
        return data.stream().map(item -> item + "-POSTFIX").collect(Collectors.toList());
    }

    private static void printData(final List<String> data) {
        data.forEach(System.out::println);
    }
}
