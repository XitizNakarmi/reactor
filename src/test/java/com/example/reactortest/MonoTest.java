package com.example.reactortest;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoTest {
    @Test
    public void monoTest(){
        Mono.just("A")
                .log()
                .subscribe();
    }
    @Test
    public void monoWithConsumerTest(){
        Mono.just("A")
                .log()
                .subscribe(System.out::println);// subscribe takes an implementation of consumer interface
    }
    @Test
    public void monoWithDoOnTest(){
        Mono.just("A")
                .log()
                .doOnSubscribe(subs -> System.out.println("doOnSubscribe : "+subs))
                .doOnRequest( req -> System.out.println("doOnRequest : "+req))
                .doOnSuccess( complete -> System.out.println("doOnSuccess : "+complete))
                .subscribe(System.out::println);// subscribe takes an implementation of consumer interface
    }
    @Test
    public void monoWithEmptyTest(){
        Mono.empty() // for void returns
                .log()
                .subscribe(System.out::println);// subscribe takes an implementation of consumer interface
    }
    @Test
    public void monoWithEmptyConsumerTest(){
        Mono.empty()
                .log()
                .subscribe(
                        (sub) -> System.out.println("Empty Mono "+sub),
                        null,
                        () -> System.out.println("Completed")
                );// subscribe takes an implementation of consumer interface
    }
    @Test
    public void monoWithErrorTest(){
        Mono.error(new Exception()) // for void returns
                .log()
                .subscribe(
                        System.out::println,
                        e -> System.out.println(e)
                );// subscribe takes an implementation of consumer interface
    }
    @Test
    public void monoWithOnErrorResumeTest(){
        Mono.error(new Exception())
                .onErrorResume(
                        e -> {
                            System.out.println("Error: "+e);
                            return Mono.just("b");
                        }
                )// for void returns
                .log()
                .subscribe(System.out::println);// subscribe takes an implementation of consumer interface
    }
    @Test
    public void monoWithOnErrorReturnTest(){
        Mono.error(new Exception())
                .onErrorReturn(Mono.just("ErrorReturn"))// for void returns
                .log()
                .subscribe(System.out::println);// subscribe takes an implementation of consumer interface
    }
}
