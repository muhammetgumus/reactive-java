package com.muhammetgumus;

import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

@NoArgsConstructor
public class RetryMechanism {
    public static void main(String[] args) {
        RetryMechanism rm = new RetryMechanism();
        rm.fakeServiceCallRetryExample().subscribe();
    }

    public Flux<String> fakeServiceCallRetryExample() {
        return Flux.just("1", "2", "3", "4")
                .map(e -> "Time-" + retryElementProcess(e))
                .onErrorMap(e -> {
                    System.out.println("Error!");
                    throw new RuntimeException();
                })
                .log()
                .retry(2);
    }

    public String retryElementProcess(String element) {
        if ("3".equals(element)) {
            throw new IllegalStateException("Illegal element!!");
        }
        return element;
    }
}