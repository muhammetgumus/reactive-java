package com.muhammetgumus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class RetryMechanismTest {

    @Mock
    RetryMechanism retryMechanism;

    @Test
    void fakeServiceCallRetryExample() {
        Mockito.when(retryMechanism.fakeServiceCallRetryExample()).thenCallRealMethod();
        Flux<String> stringFlux = retryMechanism.fakeServiceCallRetryExample().log();
        StepVerifier.create(stringFlux)
                .expectNextCount(4)
                .verifyComplete();
    }
}