package com.reactive.learning;

import com.reactive.learning.model.Item;
import com.reactive.learning.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.color.ProfileDataException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReactiveMain {

    public static void main(String[] args) {
        // flux producer
        Flux<String> fluxStrings = Flux.just("john","mike","bob");
        // concating values to flux
        fluxStrings = fluxStrings.concatWithValues("martin");
        // throwing exception in the values (onError() event will trigger)
        //fluxStrings = fluxStrings.concatWith(Flux.error(new RuntimeException("some exception")));
        //merging two flux
        Flux<String> newStrings = Flux.just("bobby");
        // printing logs of flux
        fluxStrings = fluxStrings.concatWith(newStrings).log();
        // subscribing to flux and handing onSucces and on Error
        fluxStrings.subscribe(System.out::println, e -> System.out.println("exception"), ()-> System.out.println("complete"));

        //delaring empty flux, you cannot add null to the values in the flxu, you will get NPE
        //Flux<String> emptyFlux = Flux.just(null) //npe
        Flux<?> emptyFlux = Flux.empty().log();
        emptyFlux.subscribe(System.out::println, e -> System.out.println("exception"), ()-> System.out.println("complete"));


        // Mono producer
        Mono<String> monoStrings = Mono.just("Rambo").log();
        // Adding exception to mono and triggering onError event
        //Mono<Object> monoStrings = Mono.error(new RuntimeException()).log();
        monoStrings.subscribe(System.out::println,e -> System.out.println("success"), ()-> System.out.println("complete"));

        //empty mono
        Mono<?> emptyMono = Mono.empty().log();
        // in mono you can null using
        //Mono<String> emptyMono = Mono.justOrEmpty(null).log(); // in this no NPE, but in FLUX you can not add nulls
        emptyMono.subscribe(System.out::println,e -> System.out.println("success"), ()-> System.out.println("complete"));


        // Optionals can be converted to mono
        Optional<String> optionalStrings = Optional.of("Ram");
        Mono<String> newMonoStrings = Mono.justOrEmpty(optionalStrings).log();
        newMonoStrings.subscribe(System.out::println,e -> System.out.println("success"), ()-> System.out.println("complete"));


        //List to Flux
        List<String> listStrings = Arrays.asList("mam","nemo");
        Flux<String> stringFlux = Flux.fromIterable(listStrings).log();
        stringFlux.subscribe(System.out::println,e -> System.out.println("error"), ()-> System.out.println("complete"));


        //Stream to Flux
        Stream<String> stringStream = Stream.of("ram","remo");
        Flux<String> fluxString1 = Flux.fromStream(stringStream).log();
        fluxString1.subscribe(System.out::println, e -> System.out.println("error"), ()->System.out.println("comletion"));

        // flux using range
        Flux<Integer> rangeFlux = Flux.range(10,5).log();
        rangeFlux.subscribe(System.out::println, e -> System.out.println("error"), ()->System.out.println("comletion"));

        // flux using filter
        Flux<Integer> filterFlux = Flux.range(10,10).filter(i->i%2==0).filter(i->i*2 > 0).log();
        filterFlux.subscribe(System.out::println, e -> System.out.println("error"), ()->System.out.println("comletion"));

        //List vs Flux
        // here in list inside map will print first, where in flux inside map prints for every event.
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "oil"));
        products.add(new Product(2, "milk"));
        products.add(new Product(3, "bread"));

        List<String> productsList = products.stream().filter(i -> i.getProductId() > 0 ).map(i-> { System.out.println("inside map");
        return i.getProductName(); }).collect(Collectors.toList());

        productsList.forEach(System.out::println);

        System.out.println("----flux-----");

        Flux<String> fluxProducts = Flux.just(
                new Product(1, "oil"),
                new Product(2, "milk"),
                new Product(3, "bread")).filter(i -> i.getProductId() > 0 ).map(i-> { System.out.println("inside map");
                    return i.getProductName(); });
        fluxProducts.subscribe(System.out::println);;

        // Flat map with Flux
        Flux<Long> longFlux = Flux.just(1L, 2L, 3L);
        Flux<Flux<Item>> itemFlux1 = longFlux.map(id -> getItems(id));
        Flux<Item> itemFlux = longFlux.flatMap(orderId -> getItems(orderId));
        itemFlux.subscribe(item -> System.out.println(item.getName()));




    }

    public static Flux<Item> getItems(Long orderId) {
        return Flux.just(
                new Item(1, "Item-1"),
                new Item(2, "Item-2"),
                new Item(3, "Item-3")
        );
    }
}
