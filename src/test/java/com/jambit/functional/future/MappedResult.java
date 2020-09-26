package com.jambit.functional.future;

import io.vavr.Tuple3;
import io.vavr.concurrent.Future;
import io.vavr.control.Option;
import lombok.Data;

@Data
public class MappedResult {

    private final int id;
    private final String detail;
    private final String relation;
    private final String desktopImageUrl;
    private final String mobileImageUrl;

    static MappedResult createFrom(final Tuple3<Detail, Option<Relation>, Future<Images>> tuple) {
        return new MappedResult(
                tuple._1().getId(),
                tuple._1().getName(),
                tuple._2().map(Relation::getName).getOrNull(),
                tuple._3().map(maybe -> maybe.getDesktopUrl().getUrl()).getOrNull(),
                tuple._3().map(maybe -> maybe.getMobileUrl().getUrl()).getOrNull());
    }
}
