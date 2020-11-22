package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    // Список всех товаров
    @GetMapping
    public String showAll(Model model) {
        List<Product> products = productService.getProductList();
        model.addAttribute("products", products);

        // WEB-INF/templates/products.html
        return "products";
    }

    // Форма добавления товара
    @GetMapping("/add")
    public String showAddForm() {
        return "product_add_form";
    }

    // Обработка запроса из формы
    @PostMapping("/add")
    public String addProduct(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String brand,
            @RequestParam Double price
    ) {
        Product product = new Product(id, title, description, brand, price);
        productService.saveOrUpdate(product);
        return "redirect:/products/";
    }

    // Форма редактивания товара
    @GetMapping("/edit/{id}")
    public String showEditForm (@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product_edit_form";
    }

    // Запрос на адейт из формы
    @PostMapping("/edit")
    public String modifyCustomer(
            @ModelAttribute Product modifiedProduct
    ) {
        productService.saveOrUpdate(modifiedProduct);
        return "redirect:/products/";
    }

    // Страница для отображения конкретного товара
    @GetMapping("/show/{id}")
    public String showProductPage (@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product";
    }
}
