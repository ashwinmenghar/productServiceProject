package dev.ashwin.productservicettsevening.clients.fakeStoreApi;

import dev.ashwin.productservicettsevening.dtos.ProductDto;
import dev.ashwin.productservicettsevening.exceptions.NotFoundException;
import dev.ashwin.productservicettsevening.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreClient {
    RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(
               "https://fakestoreapi.com/products", FakeStoreProductDto[].class
        );
        return Arrays.asList(response.getBody());
    }

    public Optional<FakeStoreProductDto> getSingleProduct(Long productId) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class,
                productId
        );
        Optional<FakeStoreProductDto> productDtoOptional = Optional.ofNullable(response.getBody());

        if(productDtoOptional.isEmpty()) {
            throw new NotFoundException("Product with id " + productId + " not found");
        }

        return productDtoOptional;
    }

    public FakeStoreProductDto addNewProduct(ProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto>  response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                product,
                FakeStoreProductDto.class
        );

        return response.getBody();
    }


    public FakeStoreProductDto updateProduct(Long productId, FakeStoreProductDto fakeStoreProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        // Approach 1
        //        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(
//                HttpMethod.PATCH,
//                "https://fakestoreapi.com/products/{id}",
//                fakeStoreProductDto,
//                FakeStoreProductDto.class,
//                productId
//        );
//
//        if (fakeStoreProductDtoResponseEntity.getHeaders()){}
//
//
        // Approach 2
        FakeStoreProductDto fakeStoreProductDtoResponse = restTemplate.patchForObject(
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );

        return fakeStoreProductDtoResponse;
    }

    String replaceProduct(Long productId) {
        return null;
    }

    public FakeStoreProductDto deleteProduct(Long productId) {
        RestTemplate restTemplate = new RestTemplate();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(
                "https://fakestoreapi.com/products/{id}",
                HttpMethod.DELETE,
                requestCallback,
                responseExtractor,
                productId
        );
        return response.getBody();
    }
}
