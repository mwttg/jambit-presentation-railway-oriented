package com.jambit.object.oriented2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context =
                new SpringApplicationBuilder(com.jambit.object.oriented2.Application.class).run(args);
    }
}
