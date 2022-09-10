package com.muhammetgumus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ProgrammaticallyFluxMonoCreationTest {

    @Mock
    ProgrammaticallyFluxMonoCreation pfmc;

    @Test
    void programmaticallyFluxGeneration() {
        Mockito.when(pfmc.programmaticallyFluxGeneration()).thenCallRealMethod();
        var flux = pfmc.programmaticallyFluxGeneration();
        StepVerifier.create(flux)
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    void programmaticallyFluxCreation(){
        Mockito.when(pfmc.programmaticallyFluxCreation()).thenCallRealMethod();
        var flux = pfmc.programmaticallyFluxCreation();
        StepVerifier.create(flux)
                .expectNextCount(3)
                .verifyComplete();
    }
    @Test
    void programmaticallyFluxCreationWithAsync(){
        Mockito.when(pfmc.programmaticallyFluxCreationWithAsync()).thenCallRealMethod();
        var flux = pfmc.programmaticallyFluxCreationWithAsync();
        StepVerifier.create(flux)
                .expectNextCount(3)
                .verifyComplete();
    }
}