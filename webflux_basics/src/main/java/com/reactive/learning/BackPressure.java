package com.reactive.learning;

import org.reactivestreams.Subscription;
import reactor.core.Exceptions;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import javax.xml.validation.Validator;

public class BackPressure {
    public static void main(String[] args) {
        Flux<Integer> fluxIntegers = Flux.range(5,10).log();

        fluxIntegers.subscribe(new BaseSubscriber<Integer>(){

            protected void hookOnSubscribe(Subscription subscription){
                subscription.request(5);
            }

            protected void hookOnNext(Integer value){
                if(value == 8){
                    cancel();
                }
                System.out.println(value);
            }

            protected void hookOnComplete() {

                System.out.println("completed");
            }

            protected void hookOnError(Throwable throwable) {
                throw new RuntimeException("error");
            }


        });
    }
}
