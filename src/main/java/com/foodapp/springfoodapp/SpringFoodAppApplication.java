package com.foodapp.springfoodapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
//@EnableCaching
public class SpringFoodAppApplication {
//    private static final ByteBuf CONTENE_BUFFER = Unpooled.copiedBuffer("Helo from Netty", StandardCharsets.UTF_8).asReadOnly();
//    private static final String METRICS_PATH = "/actuator/prometheus";
//    private static final CollectorRegistry RIGISTRY= CollectorRegistry.deafaultRegistry;
//    private static final int port=8080;
//    private static final Counter HTTP_REQUEST_TOTAL = Counter.build()
//            .name("http_request_total")
//            .help("Total number of HTTP requests")
//            .register(RIGISTRY);

    public static void main(String[] args) {
        SpringApplication.run(SpringFoodAppApplication.class, args);

//        DefaultExports.initialize();

    }


}
