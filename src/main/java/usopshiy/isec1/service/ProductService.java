package usopshiy.isec1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import usopshiy.isec1.entity.Product;
import usopshiy.isec1.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().peek(
                it -> it.setName(HtmlUtils.htmlEscape(it.getName()))
        ).toList();
    }
}
