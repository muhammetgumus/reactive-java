package com.muhammetgumus;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class HotAndColdStreams {
    public static void main(String[] args) {
        try {
            //coldStream();
            //hotStream();
            autoConnectExample();
            Thread.sleep(20000); //Added for test aim because of to see the all results until processes are done
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }

    }

    public static void coldStream() {

        integerFlux().subscribe(item -> {
            System.out.println("Subscriber 1 : - " + item);
        });
        integerFlux().subscribe(item -> {
            System.out.println("Subscriber 2 : - " + item);
        });
    }

    public static void hotStream() throws InterruptedException {
        //.publish() returns flux to the hot stream. Then we need to connect and subscribe to hot stream
        ConnectableFlux<Integer> connectableFlux = integerFluxWithDelay().publish();
        connectableFlux.connect();
        connectableFlux.subscribe(item -> {
            System.out.println("HotSubscriber1 - Item : " + item);
        });
        Thread.sleep(5000);

        connectableFlux.subscribe(item -> {
            System.out.println("HotSubscriber2 - Item : " + item);
        });
    }

    public static void autoConnectExample() throws InterruptedException {
        //autoConnect() will emit values after reaching the min subscribers count
        Flux<Integer> flux = integerFluxWithDelay().publish().autoConnect(3);

        flux.subscribe(item -> {
            System.out.println("Subscriber 1: Item is : " + item);
        });

        System.out.println("First subscriber ready");
        Thread.sleep(3000);
        System.out.println("Second subscriber ready");

        flux.subscribe(item -> {
            System.out.println("Subscriber 2: Item is : " + item);
        });

        Thread.sleep(2000);
        System.out.println("Third subscriber ready");
        flux.subscribe(item -> {
            System.out.println("Subscriber 3: Item is : " + item);
        });
    }

    private static Flux<Integer> integerFlux() {
        return Flux.range(1, 15);
    }

    private static Flux<Integer> integerFluxWithDelay() {
        return Flux.range(1, 10).delayElements(Duration.ofMillis(1000));
    }
}
