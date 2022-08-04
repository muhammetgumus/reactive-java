package com.muhammetgumus;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class ReactiveExceptionHandlingTest extends TestCase {

    @Test
    public void testOnErrorReturnExample() {
        StepVerifier.create(ReactiveExceptionHandling.onErrorReturnExample())
                .expectNext("Person1","Person2","Person3","Person4")
                .verifyComplete();
    }
}