package dev.ashwin.productservicettsevening.controllers;

import dev.ashwin.productservicettsevening.dtos.ProductDto;
import dev.ashwin.productservicettsevening.models.Product;
import dev.ashwin.productservicettsevening.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController( ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add( "auth-token", "ashwin");

        ResponseEntity<Product> response = new ResponseEntity(
                productService.getSingleProduct(productId),
                headers,
                HttpStatus.OK
        );

        return response;
    }


    @PostMapping()
    public Product addNewProduct(@RequestBody ProductDto product) {
        Product newProduct = productService.addNewProduct(product);
        return newProduct;
    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId) {
        return "Updating product";
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        return "Deleting a product with id: " + productId;
    }
}
