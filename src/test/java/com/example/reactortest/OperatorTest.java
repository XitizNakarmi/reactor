package com.example.reactortest;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class OperatorTest {
    @Test
    public void map(){
        Flux.range(1,5)
                .log()
                .map( x -> x*10)
                .subscribe(System.out::println);
    }
    @Test
    public void flatMap(){
        Flux.range(1,5)
                .flatMap( x -> Flux.range(x*10, 2))
                .subscribe(System.out::println);
    }
//    @Test
//    public void flatMapMono(){
//        Mono.just(3)
//                .flatMap( i -> Flux.range(1,i))
//                .subscribe(System.out::println);
//    }
    @SneakyThrows
    @Test
    public void concat(){
        Flux<Integer> fl1 = Flux.range(1,5)
                .delayElements(Duration.ofMillis(200));
        Flux<Integer> fl2 = Flux.range(6,5)
                .delayElements(Duration.ofMillis(100));
        Flux.concat(fl1,fl2)
                .subscribe(System.out::println);
        Thread.sleep(4000);
    }
    @SneakyThrows
    @Test
    public void merge(){
        Flux<Integer> fl1 = Flux.range(1,5)
                .delayElements(Duration.ofMillis(200));
        Flux<Integer> fl2 = Flux.range(6,5)
                .delayElements(Duration.ofMillis(400));
        Flux.merge(fl1,fl2)
                .subscribe(System.out::println);
        Thread.sleep(4000);
    }
    @Test
    public void zip(){
        Flux<Integer> fl1 = Flux.range(1,5);
        Flux<Integer> fl2 = Flux.range(6,5);
        Flux.zip(fl1,fl2, (item1, item2) -> item1+","+item2)
                .subscribe(System.out::println);
    }
}
