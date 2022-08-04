package com.muhammetgumus;

import reactor.core.publisher.Flux;

public class ReactiveExceptionHandling {
    public static void main(String[] args) {

    }

    public static Flux<String> fluxGenerator() {
        return Flux.just("Person1", "Person2", "Person3");
    }

    /**
     * onErrorReturn function's return value will be merge to the end of the flux
     * @return Flux<String>
     */
    public static Flux<String> onErrorReturnExample() {
        return fluxGenerator()
                .concatWith(Flux.error(new IllegalStateException("Exception occured")))
                .onErrorReturn("Person4")
                .log();
    }

}
