package dev.ashwin.productservicettsevening.services;

import dev.ashwin.productservicettsevening.dtos.ProductDto;
import dev.ashwin.productservicettsevening.models.Category;
import dev.ashwin.productservicettsevening.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto[]> response = restTemplate.getForEntity(
               "https://fakestoreapi.com/products", ProductDto[].class
        );

        List<Product> answer = new ArrayList<>();

        for(ProductDto res : response.getBody()) {
            Product product = new Product();

            product.setId(res.getId());
            product.setTitle(res.getTitle());
            product.setDescription(res.getDescription());
            product.setPrice(res.getPrice());
            product.setImageUrl(res.getImage());

            Category category = new Category();
            category.setName(res.getCategory());
            product.setCategory(category);

            answer.add(product);
        }
        return answer;
    }

    @Override
    public Product getSingleProduct(Long productId) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                ProductDto.class,
                productId
        );

        ProductDto productDto = response.getBody();
        Product product = new Product();

        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        Category category = new Category();
        category.setName(productDto.getCategory());

        product.setCategory(category);
        product.setImageUrl(productDto.getImage());

        return product;
    }

    @Override
    public Product addNewProduct(ProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto>  response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                    product,
                    ProductDto.class
        );

        ProductDto productDto = response.getBody();
        Product newProduct = new Product();

        newProduct.setId(productDto.getId());
        newProduct.setTitle(productDto.getTitle());
        newProduct.setDescription(productDto.getDescription());
        newProduct.setPrice(productDto.getPrice());
        newProduct.setImageUrl(productDto.getImage());

        Category category = new Category();
        category.setName(productDto.getCategory());
        newProduct.setCategory(category);

        return newProduct;
    }

    @Override
    public String updateProduct(Long productId) {
        return null;
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }
}
