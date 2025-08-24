package com.learnreactiveprogramming.servicet;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

@Slf4j
class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {

        var namesFlux = fluxAndMonoGeneratorService.namesFlux();


        StepVerifier.create(namesFlux)
                .expectNext("alex","ben","chloe")
                .verifyComplete();

        StepVerifier.create(namesFlux)
                .expectNextCount(3)
                .verifyComplete();


    }

    @Test
    void nameMono() {

        Mono<String> mono = fluxAndMonoGeneratorService.nameMono();

        StepVerifier.create(mono)
                .expectNext("alex")
                .verifyComplete();

    }    @Test
    void nameFluxUpper() {

        Flux<String> mono = fluxAndMonoGeneratorService.namesFlux_UpperCase();

        StepVerifier.create(mono)
                .expectNext("ALEX","BEN","CHLOE")
                .verifyComplete();

    }

    @Test
    void nameFlux_immutability() {

        Flux<String> flux=fluxAndMonoGeneratorService.nameFlux_immutability();

        StepVerifier.create(flux)
                .expectNext("alex","ben","chloe")
                .verifyComplete();


    }

    @Test
    void nameFlux_filterGreaterThan() {

        Flux<String> flux=fluxAndMonoGeneratorService.nameFlux_filterGreaterThan();

        StepVerifier.create(flux)
                .expectNext("ALEX","CHLOE")
                .verifyComplete();


    }

    @Test
    void nameFlux_flatmap() {

        int stringLength=3;

        Flux<String> flux=fluxAndMonoGeneratorService
                .nameFlux_flatmap(stringLength);

        StepVerifier.create(flux)
                .expectNext("A","L","E","X","C","H","L","O","E")
                .verifyComplete();
    }

    @RepeatedTest(10)
    void nameFlux_flatmapAsync() {

        int stringLength=3;

        Flux<String> flux=fluxAndMonoGeneratorService
                .nameFlux_flatmapAsync(stringLength);

        StepVerifier.create(flux)
                .expectNextCount(9)
                .verifyComplete();

    }
    @RepeatedTest(10)
    void nameFlux_flatmapAsyncOrdered() {

        int stringLength=3;

        Flux<String> flux=fluxAndMonoGeneratorService
                .nameFlux_flatmapAsyncOrdered(stringLength);

        StepVerifier.create(flux)
                .expectNext("A","L","E","X","C","H","L","O","E")
                .verifyComplete();

    }
}
