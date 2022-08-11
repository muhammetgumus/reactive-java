package com.muhammetgumus;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

public class SchedulersOperation {

    static List<String> names1 = List.of("Adam", "Mike", "Kate");
    static List<String> names2 = List.of("Jane", "Alex", "Janet");

    public static void main(String[] args) {
        SchedulersOperation schedulersOperation = new SchedulersOperation();
        schedulersOperation.publishOnExample().log().subscribe();
    }

    public Flux<String> publishOnExample() {
        SchedulersOperation so = new SchedulersOperation();
        Flux<String> firstFlux = Flux.fromIterable(names1)
                .publishOn(Schedulers.parallel())
                .map(so::delayedUpperCase)
                .log();

        Flux<String> secondFlux = Flux.fromIterable(names2)
                .publishOn(Schedulers.parallel())
                .map(so::delayedUpperCase)
                .log();


        return firstFlux.mergeWith(secondFlux);
    }

    public String delayedUpperCase(String input) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return input.toUpperCase();
    }

}
