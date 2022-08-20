package com.muhammetgumus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.List.of;

public class SchedulersOperation {

    static List<String> names1 = of("Adam", "Mike", "Kate");
    static List<String> names2 = of("Jane", "Alex", "Janet");

    public static void main(String[] args) throws InterruptedException {
        SchedulersOperation schedulersOperation = new SchedulersOperation();
        //schedulersOperation.publishOnExample().log().subscribe();
        //schedulersOperation.subscribeOn().subscribe();
        schedulersOperation.blockingToNonBlocking().subscribe();
        Thread.sleep(5000); //Added just to slow down the main thread and see the results of the parallel thread!
        System.out.println("Main thread finished! ==> " + LocalDateTime.now() + "-" + Thread.currentThread().getName());
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

    public Flux<List<String>> blockingToNonBlocking() {
        //Be careful about return type of the fakeBlockingApiCalls(). It is not return Flux or Mono.
        return Flux.just(of("Reactor", "Java", "Example"))
                .zipWith(Mono.fromCallable(() -> fakeBlockingApiCalls())
                                .subscribeOn(Schedulers.boundedElastic()),
                        (firstList, secondList) -> {
                            List<String> str = Stream.of(firstList, secondList)
                                    .flatMap(Collection::stream)
                                    .collect(Collectors.toList());
                            return str;
                        })
                .map(element -> {
                    System.out.println(element + " - " + Thread.currentThread().getName());
                    return element;
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

    public List<String> fakeBlockingApiCalls() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return List.of("Fake", "Api", "Data");
    }
}
