package dev.ashwin.productservicettsevening.services;

import dev.ashwin.productservicettsevening.clients.fakeStoreApi.FakeStoreClient;
import dev.ashwin.productservicettsevening.clients.fakeStoreApi.FakeStoreProductDto;
import dev.ashwin.productservicettsevening.dtos.ProductDto;
import dev.ashwin.productservicettsevening.exceptions.NotFoundException;
import dev.ashwin.productservicettsevening.models.Category;
import dev.ashwin.productservicettsevening.models.Product;
import jakarta.annotation.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreProductServiceImpl implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);

    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());

        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);

        return product;
    }

    private FakeStoreProductDto convertProductToFakeStoreProductDto(Long productId, ProductDto product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(productId);
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImage());
        fakeStoreProductDto.setCategory(product.getCategory());

        return fakeStoreProductDto;
    }


    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDto = fakeStoreClient.getAllProducts();
        List<Product> answer = new ArrayList<>();

        for(FakeStoreProductDto res : fakeStoreProductDto)
            answer.add(convertFakeStoreProductDtoToProduct(res));

        return answer;
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
        Optional<FakeStoreProductDto> fakeStoreProductDto = fakeStoreClient.getSingleProduct(productId);

        if(fakeStoreProductDto == null) {
            return Optional.empty();
        }

        return Optional.of(convertFakeStoreProductDtoToProduct(fakeStoreProductDto.get()));
    }

    @Override
    public Product addNewProduct(ProductDto product) {
        return convertFakeStoreProductDtoToProduct(fakeStoreClient.addNewProduct(product));
    }

    @Override
    public Product updateProduct(Long productId, ProductDto product) {
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreProductDto(productId, product);

        try {
            return convertFakeStoreProductDtoToProduct(fakeStoreClient.updateProduct(productId, fakeStoreProductDto));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Product replaceProduct(Long productId, ProductDto product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long productId) throws NotFoundException {
        try{
            FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.deleteProduct(productId);
            return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        } catch (Exception e) {
            throw new NotFoundException("Product with id " + productId + " not found for delete operation");
        }

    }
}
