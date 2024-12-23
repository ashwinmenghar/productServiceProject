package dev.ashwin.productservicettsevening.controllers;

import dev.ashwin.productservicettsevening.dtos.ProductDto;
import dev.ashwin.productservicettsevening.exceptions.NotFoundException;
import dev.ashwin.productservicettsevening.models.Product;
import dev.ashwin.productservicettsevening.services.ProductService;
import dev.ashwin.productservicettsevening.services.SelfProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        products.get(0).setPrice(100); /// Bug induced in my code
        return products;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add( "auth-token", "ashwin");

        Optional<Product> productOptional = productService.getSingleProduct(productId);

        if(productOptional.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(productOptional.get(), headers, HttpStatus.OK);
    }


    @PostMapping()
    public Product addNewProduct(@RequestBody ProductDto product) {
        return productService.addNewProduct(product);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto product) throws NotFoundException {
        return new ResponseEntity(productService.updateProduct(productId, product), HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto product) throws NotFoundException {
        return new ResponseEntity(productService.replaceProduct(productId, product), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        return new ResponseEntity(productService.deleteProduct(productId), HttpStatus.OK);
    }
}
