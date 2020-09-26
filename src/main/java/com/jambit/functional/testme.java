package com.jambit.functional;

import java.time.LocalDate;

public class testme {



    public String myAwesomeMethod(final String filename) {
        final var content = readFile(filename);
        final var enrichedContent = addData();
        final var date1 = LocalDate.now();
        final var filenameDate = date1.minusDays(1);
        final var filenameDateAsString = filenameDate.toString();
        writeFile(filenameDateAsString, enrichedContent);

        return null;
    }


    private String getLastWeek() {
        final var now = LocalDate.now();
        final var lastWeek = now.minusDays(7);
        return lastWeek.toString();
    }



    private String readFile(final String filename) {
        return null;
    }

    private String addData() {
        return null;
    }

    private void writeFile(final String date, final String content) {

    }

}
