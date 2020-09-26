package com.jambit.functional.monads;

import org.testng.annotations.Test;


@SuppressWarnings({"InfiniteLoopStatement", "CatchMayIgnoreException"})
public class KissTest {


    @Test
    public void myMethod1() {
        var count = 0;
        try {
            while (true) {
                doImportantStuff(count);
                count++;

                if (count == 10) {
                    throw new RuntimeException("we are ready!");
                }
            }
        } catch (final RuntimeException e) {

        }
    }

    @Test
    public void myMethod2() {
        for(int i = 0; i < 10; i++) {
            doImportantStuff(i);
        }
    }


    private static void doImportantStuff(final int count) {
        System.out.println("Iteration " + count);
    }
}
