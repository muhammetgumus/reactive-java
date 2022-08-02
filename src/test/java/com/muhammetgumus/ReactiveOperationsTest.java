package com.muhammetgumus;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class ReactiveOperationsTest extends TestCase {

    @Test
    public void testFluxGenerator() {
        StepVerifier.create(ReactiveOperations.fluxGenerator()).expectNext("default").verifyComplete();
    }

    @Test
    public void concatExample() {
        StepVerifier.create(ReactiveOperations.concatExample()).expectNextCount(6).verifyComplete();
    }

    @Test
    public void concatWithExample() {
        StepVerifier.create(ReactiveOperations.concatWithExample()).expectNext("A", "B", "C", "D").verifyComplete();
    }

    @Test
    public void mergeExample() {
        StepVerifier.create(ReactiveOperations.mergeExample()).expectNext("A", "D", "B", "C", "E", "F").verifyComplete();
    }

    @Test
    public void mergeWithExample() {
        StepVerifier.create(ReactiveOperations.mergeWithExample()).expectNext("A", "D", "B", "C", "E", "F").verifyComplete();
    }

    @Test
    public void mergeWithMonoExample() {
        StepVerifier.create(ReactiveOperations.mergeWithMonoExample()).expectNext("A", "B").verifyComplete();
    }

    @Test
    public void mergeSequantialExample() {
        StepVerifier.create(ReactiveOperations.mergeSequantialExample()).expectNext("A", "S", "D", "Q", "W", "E").verifyComplete();
    }

    @Test
    public void zipExample() {
        StepVerifier.create(ReactiveOperations.zipExample()).expectNext("AQ", "SW", "DE").verifyComplete();
    }
}
