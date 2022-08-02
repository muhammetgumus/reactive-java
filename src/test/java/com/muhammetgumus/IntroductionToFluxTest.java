package com.muhammetgumus;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class IntroductionToFluxTest extends TestCase {

    @Test
    public void testNames_Map() {
        StepVerifier.create(IntroductionToFlux.names_map()).expectNext("MUHAMMET", "ALI", "ROBERT",
                "IVANA", "JESSIE", "CHEN").verifyComplete();
    }
}