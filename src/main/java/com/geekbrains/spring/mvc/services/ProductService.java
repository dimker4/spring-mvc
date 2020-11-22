package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // Говорим о том, что класс будет являться компонентом спринга
public class ProductService {

    ProductRepository productRepository;

    public ProductService (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductList () {
        return productRepository.findAll();
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.saveOrUpdate(product);
    }

    public Product getProductById (Long id) {
        return productRepository.getProductById(id);
    }

}