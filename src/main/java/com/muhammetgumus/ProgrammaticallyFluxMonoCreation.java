package com.muhammetgumus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProgrammaticallyFluxMonoCreation {
    public static void main(String[] args) {
    }

    public Flux<Object> programmaticallyFluxGeneration() {
        return Flux.generate(() -> 1, (state, sink) -> {
            sink.next(state * 2);
            if (state == 10) {
                sink.complete();
            }
            return state + 1;
        }).log();
    }

    public Flux<Object> programmaticallyFluxCreation() {
        return Flux.create(sink -> {
            names().forEach(sink::next);
            sink.complete();
        }).log();
    }

    public Flux<Object> programmaticallyFluxCreationWithAsync() {
        return Flux.create(sink -> {
            CompletableFuture
                    .supplyAsync(() -> names())
                    .thenAccept(names -> {
                        names.forEach(sink::next);
                    })
                    .thenRun(sink::complete);
        }).log();
    }

    public Mono<String> programmaticallyMonoCreate(){
        return Mono.create((sink)->{
            sink.success("TestUser");
        });
    }

    public static List<String> names() {
        return List.of("Adam", "Ali", "Chen");
    }

}
