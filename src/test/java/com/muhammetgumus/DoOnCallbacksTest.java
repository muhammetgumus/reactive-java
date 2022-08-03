package com.muhammetgumus;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class DoOnCallbacksTest extends TestCase {

    @Test
    public void testLevelGenerator() {
        StepVerifier.create(DoOnCallbacks.levelGenerator()).expectNextCount(4).verifyComplete();
    }

    @Test
    public void testDoOnCallbacks() {
        StepVerifier.create(DoOnCallbacks.doOnCallbacks()).expectNext("Easy","Medium","Hard","WordClass").verifyComplete();
    }
}