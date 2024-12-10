package dev.ashwin.productservicettsevening.services;

import dev.ashwin.productservicettsevening.dtos.ProductDto;
import dev.ashwin.productservicettsevening.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(Long productId);

    Product addNewProduct(ProductDto product);

    String updateProduct(Long productId);

    String deleteProduct(Long productId);
}
