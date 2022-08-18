package com.muhammetgumus;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

public class SchedulersOperation {

    static List<String> names1 = List.of("Adam", "Mike", "Kate");
    static List<String> names2 = List.of("Jane", "Alex", "Janet");

    public static void main(String[] args) throws InterruptedException {
        SchedulersOperation schedulersOperation = new SchedulersOperation();
        //schedulersOperation.publishOnExample().log().subscribe();
        schedulersOperation.subscribeOn().subscribe();
        Thread.sleep(100); //Added just to slow down the main thread and see the results of the parallel thread!
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

    public Flux<String> subscribeOn() {
        //subscribeOn effects whole flow so that it is also effect upstream . But publishOn is effects downstream!
        System.out.println("We are in the thread ==> \"" + Thread.currentThread().getName() + "\" at the first enter to the subscribeOn method!");
        return Flux.fromIterable(names1)
                .map(name -> {
                    System.out.println("First Map value is : " + name + " and mapped from -> " + Thread.currentThread().getName());
                    return name.toUpperCase();
                })
                .subscribeOn(Schedulers.parallel())
                .map(name -> {
                    System.out.println("Second Map value is : " + name + " and mapped from -> " + Thread.currentThread().getName());
                    return name;
                })
                .log();
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
