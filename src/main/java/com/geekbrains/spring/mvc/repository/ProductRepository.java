package com.geekbrains.spring.mvc.repository;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> productList = new ArrayList<>();

    @PostConstruct // метод будет вызывается после вызова конструктора
    public void init() {
        productList.add(new Product(1l, "apple", "Яблоко", "Auchan", 50.0));
        productList.add(new Product(2l, "lemon", "Лемон", "Auchan", 150.0));
        productList.add(new Product(3l, "orange", "Апельсин", "semya", 75.0));
        productList.add(new Product(4l, "potato", "Картоха", "5ka", 25.0));
        productList.add(new Product(5l, "watermelon", "Арбуз", "magnit", 110.0));
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(productList);
    }

    public Product getProductById (long id) {
        for (Product product: productList) {
            if (product.getId() == id) return product;
        }
        return null;
    }

    public Product saveOrUpdate(Product customer) {
        if (customer.getId() == null) {
            customer.setId(productList.size()+1L);
            productList.add(customer);
            return customer;
        } else {
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getId().equals(customer.getId())) {
                    productList.set(i, customer);
                    return customer;
                }
            }
        }
        throw new RuntimeException("Error save or update customer");
    }

    public void addProduct(Product product) {
        productList.add(product);
    }
}
