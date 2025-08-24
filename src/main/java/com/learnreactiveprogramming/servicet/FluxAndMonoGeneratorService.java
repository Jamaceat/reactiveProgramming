package com.learnreactiveprogramming.servicet;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux() {

        return Flux.fromIterable(Arrays.asList("alex", "ben", "chloe"));

    }

    public Mono<String> nameMono() {

        return Mono.just("alex").log();
    }

    public Flux<String> namesFlux_UpperCase(){

        return  Flux.fromIterable(Arrays.asList("alex","ben","chloe"))
                .log()
                .map(String::toUpperCase).log();
    }


    public Flux<String> nameFlux_immutability(){
        var flujo =  Flux.fromIterable(Arrays.asList("alex","ben","chloe"))
                .log();

        flujo.map(String::toUpperCase).log();

        return flujo;

    }

    public Flux<String> nameFlux_filterGreaterThan(){
        return Flux.fromIterable(Arrays.asList("alex","ben","chloe"))
                .log()
                .map(String::valueOf)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .log()
                .filter(name-> name.length()>3)
                .log();


    }
    public Flux<String> nameFlux_flatmap(int stringLength){
        return Flux.fromIterable(Arrays.asList("alex","ben","chloe"))
                .log()
                .map(String::toUpperCase)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .log()
                .filter(name-> name.length()>stringLength)
                .flatMap(this::splitString) // A, L,E,X,C,H,L,O,E
                .log();


    }
    public Flux<String> nameFlux_flatmapAsync(int stringLength){



        return Flux.fromIterable(Arrays.asList("alex","ben","chloe"))
                .log()
                .map(String::toUpperCase)
                .log()
                .filter(name-> name.length()>stringLength)
                .flatMap(this::splitStringWithDelay) // A, L,E,X,C,H,L,O,E
                .log();


    }

    public Flux<String> nameFlux_flatmapAsyncOrdered(int stringLength){



        return Flux.fromIterable(Arrays.asList("alex","ben","chloe"))
                .log()
                .map(String::toUpperCase)
                .log()
                .filter(name-> name.length()>stringLength)
                .concatMap(this::splitStringWithDelay) // A, L,E,X,C,H,L,O,E
                .log();


    }

    public Flux<String> splitString(String name){

       String[] characters= name.split("");
       return Flux.fromArray(characters);
    }

    public Flux<String> splitStringWithDelay(String name){
        int random = new Random().nextInt(1000);
       String[] characters= name.split("");
       return Flux.fromArray(characters)
               .delayElements(Duration.ofMillis(random));
    }
    public Flux<String> splitStringWithDelaySingleSecond(String name){
//        int random = new Random().nextInt(1000);
       String[] characters= name.split("");
       return Flux.fromArray(characters)
               .delayElements(Duration.ofMillis(1000));
    }

        public Mono<List<String>> namesMono_flatMap(int stringLength){

        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s-> s.length()>stringLength)
                .flatMap(this::splitStringMono);

        }

        public Mono<List<String>> splitStringMono(String s){

        return Mono.just(Arrays.asList(s.split("")));

        }


    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

        fluxAndMonoGeneratorService.namesFlux().log().subscribe(System.out::println);

        fluxAndMonoGeneratorService.nameMono().log().subscribe(System.out::println);


    }

}
