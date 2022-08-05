package com.muhammetgumus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveExceptionHandling {
    public static String testMonoString = "abc";

    public static void main(String[] args) {

    }

    public static Flux<String> fluxGenerator() {
        return Flux.just("Person1", "Person2", "Person3");
    }

    public static Flux<String> onErrorReturnExample() {
        return fluxGenerator()
                .concatWith(Flux.error(new IllegalStateException("Exception occured")))
                .onErrorReturn("Person4")
                .log();
    }

    public static Flux<String> onErrorResumeExample() {
        var recoveryFlux = Flux.just("Person4", "Person5");
        return fluxGenerator()
                .concatWith(Flux.error(new IllegalStateException("Error occured")))
                .onErrorResume(e -> {
                    System.out.println("Error is : " + e);
                    return recoveryFlux;
                }).log();
    }

    public static Flux<String> onErrorContinueExample() {
        //You should use onErrorContinue() if you want to emit the remaining elements even the error is occurs
        return fluxGenerator()
                .map((person) -> {
                    if ("Person2".equals(person)) {
                        throw new IllegalStateException("Error : This person is not suitable");
                    }
                    return person.toUpperCase();
                }).onErrorContinue((err, person) -> {
                    System.out.println("Error details : " + err);
                    System.out.println(person + " -> is not suitable in here");
                }).log();

    }

    public static Flux<String> onErrorMapExample() {
        return fluxGenerator().map((person) -> {
                    if ("Person2".equals(person)) {
                        throw new IllegalStateException("Error occured and be careful this error will be changed in future !!");
                    }
                    return person;
                }).concatWith(Flux.just("Person4"))
                .onErrorMap(err -> {
                    return new IllegalAccessException(err.getClass() + " error type is the new expected type error BE CAREFUL! ");
                }).log();
    }

    public static Flux<String> doOnErrorExample() {
        //doOnError does not recover the remained parts it just catches error and then propagates it. It likes basic try-catch
        return fluxGenerator()
                .concatWith(Flux.error(new Exception("This is test exception")))
                .doOnError((err) -> {
                    System.out.println("Error occurred : While catching error in here you could add your logic here");
                })
                .log();
    }

    public static Mono<String> monoOnErrorContinue(String monoString) {
        Mono<String> abcMono = Mono.just(monoString);
        return abcMono.map(x -> {
            if ("abc".equals(x)) {
                throw new RuntimeException("Not appropriate!!");
            }
            return x;
        }).onErrorContinue((err, abcString) -> {
            System.out.println("Error is : " + err.getMessage());
            System.out.println("Element is : " + abcString);
        }).log();
    }


}
