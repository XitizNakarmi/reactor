package com.example.reactortest.Controller;

import com.example.reactortest.Model.Product;
import com.example.reactortest.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public Flux<Product> getAllProduct(){
        return productRepository.findAll();
    }
    @GetMapping("{id}")
    public Mono<ResponseEntity<Product>> getProductByName(@PathVariable String id){
        return productRepository.findById(id)
                .map( product -> ResponseEntity.ok(product))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> saveProduct(@RequestBody Product product){
        return productRepository.save(product);
    }
    @PutMapping("{id}")
    public Mono<ResponseEntity<Product>> saveProduct(@PathVariable String id, @RequestBody Product product){
        return productRepository.findById(id)
                .flatMap( existingProduct ->{
                            existingProduct.setName(product.getName());
                            existingProduct.setPrice(product.getPrice());
                            return productRepository.save(existingProduct);
                        }
                )
                .map( updatedProduct -> ResponseEntity.ok(updatedProduct))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
