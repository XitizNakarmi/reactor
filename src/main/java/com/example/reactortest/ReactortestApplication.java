package com.example.reactortest;

import com.example.reactortest.Model.Product;
import com.example.reactortest.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactortestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReactortestApplication.class, args);
    }
    @Bean
    CommandLineRunner init(ProductRepository repository){
        return args -> {
            Flux<Product> productFlux = Flux.just(
              new Product(null, "Tie", 20.23),
              new Product(null, "Shirt", 30.45),
              new Product(null, "Pant", 45.55)
            ).flatMap( repository::save );

            productFlux.thenMany(repository.findAll())
                    .subscribe(System.out::println);
        };
    }

}
