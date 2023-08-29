package com.example.springreactivemongocrud.controller;


import com.example.springreactivemongocrud.dto.ProductDto;
import com.example.springreactivemongocrud.entity.Product;
import com.example.springreactivemongocrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public Flux<ProductDto> getAllProducts(){
        return service.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getProductById(@PathVariable String id){
        return service.getProduct(id);
    }

    @GetMapping("/product-range")
    public Flux<ProductDto> getProductByPriceRange(@RequestParam("min") double min, @RequestParam("max") double max){
        return service.getProductInRange(min, max);
    }

    @PostMapping("/save")
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtoMono){
        return service.saveProduct(productDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable String id){
        return service.updateProduct(productDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return service.deleteProduct(id);
    }
}
