package dev.ashwin.productservicettsevening.services;

import dev.ashwin.productservicettsevening.dtos.ProductDto;
import dev.ashwin.productservicettsevening.exceptions.NotFoundException;
import dev.ashwin.productservicettsevening.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class NewProductService {
    public List<Product> getAllProducts() {
        return List.of();
    }
}
