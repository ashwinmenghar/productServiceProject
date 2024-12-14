package dev.ashwin.productservicettsevening.repositories;

import dev.ashwin.productservicettsevening.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);
    Product findProductById(Long id);
}
