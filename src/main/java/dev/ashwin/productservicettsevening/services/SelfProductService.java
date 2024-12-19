package dev.ashwin.productservicettsevening.services;

import dev.ashwin.productservicettsevening.dtos.ProductDto;
import dev.ashwin.productservicettsevening.exceptions.NotFoundException;
import dev.ashwin.productservicettsevening.models.Product;
import dev.ashwin.productservicettsevening.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

public class SelfProductService implements ProductService {

    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
        return Optional.empty();
    }

    @Override
    public Product addNewProduct(ProductDto product) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, ProductDto product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long productId, ProductDto product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long productId) {
        return null;
    }
}
