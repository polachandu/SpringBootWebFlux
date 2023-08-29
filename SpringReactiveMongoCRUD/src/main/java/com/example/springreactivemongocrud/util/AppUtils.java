package com.example.springreactivemongocrud.util;

import com.example.springreactivemongocrud.dto.ProductDto;
import com.example.springreactivemongocrud.entity.Product;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static ProductDto productToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        return productDto;
    }

    public static Product productDtoToProduct(ProductDto productDto){
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);
        return product;
    }
}
