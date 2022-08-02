package com.muhammetgumus;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Function;

public class ReactiveOperations {
    public static void main(String[] args) {
        fluxGenerator().doOnNext((x) -> System.out.println("Element is arrived : " + x)).subscribe();
    }

    public static Flux<String> fluxGenerator() {
        Function<Flux<String>, Flux<String>> mapFilterTransform = name ->
                name.map(String::toUpperCase)
                    .filter(x -> x.length() > 4)  // Change 2 to 7 etc.
                    .flatMap(rec -> Flux.fromArray(rec.split("")));

        var defaultFlux = Flux.just("default").transform(mapFilterTransform);

        return Flux.fromIterable(List.of("Ash", "Brook", "Mislee"))
                .transform(mapFilterTransform)
                .switchIfEmpty(defaultFlux);
                //.defaultIfEmpty("default");
    }

}
