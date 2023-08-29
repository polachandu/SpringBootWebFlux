package com.example.springreactivemongocrud.service;

import com.example.springreactivemongocrud.dto.ProductDto;
import com.example.springreactivemongocrud.repo.ProductRepository;
import com.example.springreactivemongocrud.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> getProducts(){
        return  productRepository.findAll().map(AppUtils::productToProductDto);
    }

    public Mono<ProductDto> getProduct(String id){
        return productRepository.findById(id).map(AppUtils::productToProductDto);
    }

    public Flux<ProductDto> getProductInRange(double min, double max){
        return productRepository.findByPriceBetween(Range.closed(min,max)).map(AppUtils::productToProductDto);
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
        return productDtoMono.map(AppUtils::productDtoToProduct)
                .flatMap(productRepository::insert)
                .map(AppUtils::productToProductDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id){
        return productRepository.findById(id)
                .flatMap(product -> productDtoMono.map(AppUtils::productDtoToProduct))
                .doOnNext(e -> e.setId(id))
                .flatMap(productRepository::save)
                .map(AppUtils::productToProductDto);
    }

    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id);

    }
}
