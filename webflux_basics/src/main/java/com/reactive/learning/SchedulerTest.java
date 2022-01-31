package com.reactive.learning;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulerTest {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("main" + Thread.currentThread().getName());

        Flux<Integer> fluxIntegers = Flux.just(1,2,3,4,5,6);
        fluxIntegers = fluxIntegers
                .publishOn(Schedulers.single())
                .filter(i -> {
                    System.out.println("filter" + Thread.currentThread().getName());
                    return i%2 ==0;
                })
                .publishOn(Schedulers.single())
                .map(i-> {
                    System.out.println("map" + Thread.currentThread().getName());
            return i;
        });

        fluxIntegers.subscribe(i->{
            System.out.println("sub" + Thread.currentThread().getName());
            System.out.println(i);
        });

        Thread.sleep(3000);
    }

}
