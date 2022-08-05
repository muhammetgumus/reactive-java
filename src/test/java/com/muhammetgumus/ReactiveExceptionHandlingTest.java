package com.muhammetgumus;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ReactiveExceptionHandlingTest extends TestCase {

    @Test
    public void testOnErrorReturnExample() {
        //onErrorReturn returns single value so that just "Person4" concat to the successfully emitted items
        StepVerifier.create(ReactiveExceptionHandling.onErrorReturnExample())
                .expectNext("Person1", "Person2", "Person3", "Person4")
                .verifyComplete();
    }

    @Test
    public void testOnErrorResumeExample() {
        StepVerifier.create(ReactiveExceptionHandling.onErrorResumeExample())
                .expectNext("Person1", "Person2", "Person3", "Person4", "Person5")
                .verifyComplete();
    }

    @Test
    public void testOnErrorContinueExample() {
        StepVerifier.create(ReactiveExceptionHandling.onErrorContinueExample())
                .expectNext("PERSON1", "PERSON3")
                .verifyComplete();
    }

    @Test
    public void testOnErrorMapExample() {
        //onErrorMap converts the error but cut the process after error !!
        StepVerifier.create(ReactiveExceptionHandling.onErrorMapExample())
                .expectNext("Person1")
                .expectError(IllegalAccessException.class)
                .verify();
    }

    @Test
    public void doOnErrorExample() {
        StepVerifier.create(ReactiveExceptionHandling.doOnErrorExample())
                .expectNext("Person1", "Person2", "Person3")
                .expectError(Exception.class)
                .verify();
    }

    @Test
    public void monoOnErrorContinue_JustCompleteSignal() {
        StepVerifier.create(ReactiveExceptionHandling.monoOnErrorContinue("abc"))
                .verifyComplete();
    }

    @Test
    public void monoOnErrorContinue_Passed() {
        StepVerifier.create(ReactiveExceptionHandling.monoOnErrorContinue("reactor"))
                .expectNext("reactor")
                .verifyComplete();
    }

}