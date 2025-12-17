package usopshiy.isec1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import usopshiy.isec1.entity.Product;
import usopshiy.isec1.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/data")
    public List<Product> getData() {
        return productService.getAllProducts();
    }
}
