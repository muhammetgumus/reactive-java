package com.muhammetgumus;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class ReactiveOperationsTest extends TestCase {

    @Test
    public void testFluxGenerator() {
        StepVerifier.create(ReactiveOperations.fluxGenerator()).expectNext("default").verifyComplete();
    }
}