package com.muhammetgumus;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackpressureMechanism {
    public static void main(String[] args) {
        introductionToBackpressure()
                .onBackpressureDrop(item -> {
                    //After request number "n" element will be dropped!
                    System.out.println("Item dropped : " + item);
                })
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(5);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("Next value is : " + value);
                        //below commented for testing Backpressure Drop functionality
                        /*if (value % 5 == 0) {
                            System.out.println("\n*** New request sending to publisher! ***\n");
                            request(5);
                        }*/
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("Process completed");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        System.out.println("Error occurred!");
                    }

                    @Override
                    protected void hookOnCancel() {
                        System.out.println("Process is canceled!");

                    }
                });
    }

    public static Flux<Integer> introductionToBackpressure() {
        return Flux.range(1, 20)
                .log();
    }
}
