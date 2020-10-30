package com.example.reactortest;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

public class FluxTest {
    @Test
    public void fluxTest(){
        Flux.just("a","b","c")
                .log()
                .subscribe(System.out::println);
    }
    @Test
    public void fluxFromIterable(){
        Flux.fromIterable(Arrays.asList("a","b","c"))
                .log()
                .subscribe(System.out::println);
    }
    @Test
    public void fluxFromRange(){
        Flux.range(1, 20)
                .log()
                .subscribe(System.out::println);
    }
    @Test
    public void fluxFromInterval() throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1))
                .log()
                .subscribe(System.out::println);
        Thread.sleep(5000);
    }
}
