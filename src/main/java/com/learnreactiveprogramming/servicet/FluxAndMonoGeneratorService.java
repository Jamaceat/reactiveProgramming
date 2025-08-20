package com.learnreactiveprogramming.servicet;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux() {

        return Flux.fromIterable(Arrays.asList("alex", "ben", "chloe"));

    }

    public Mono<String> nameMono() {

        return Mono.just("alex").log();
    }

    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

        fluxAndMonoGeneratorService.namesFlux().log().subscribe(System.out::println);

        fluxAndMonoGeneratorService.nameMono().log().subscribe(System.out::println);


    }

}
