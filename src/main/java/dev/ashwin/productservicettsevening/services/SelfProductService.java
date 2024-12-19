package dev.ashwin.productservicettsevening.services;

import dev.ashwin.productservicettsevening.clients.fakeStoreApi.FakeStoreProductDto;
import dev.ashwin.productservicettsevening.dtos.ProductDto;
import dev.ashwin.productservicettsevening.exceptions.NotFoundException;
import dev.ashwin.productservicettsevening.models.Category;
import dev.ashwin.productservicettsevening.models.Product;
import dev.ashwin.productservicettsevening.repositories.CategoryRepository;
import dev.ashwin.productservicettsevening.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service(value = "SelfProductService")
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private Category getCategoryToBeInProduct(ProductDto product) {
        String categoryName = product.getCategory();

        Optional<Category> category =
                categoryRepository.findByName(categoryName);
        Category toBePutInProduct = null;

        if (category.isEmpty()) {
            Category toSaveCategory = new Category();
            toSaveCategory.setName(categoryName);

            toBePutInProduct = toSaveCategory;
        } else {
            toBePutInProduct = category.get();
        }

        return toBePutInProduct;
    }

    private Product convertProductDtoToProduct(ProductDto productDto) {
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

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
        Product product = productRepository.findProductById(productId);

        if (product == null) {
            throw new NotFoundException("Product Doesn't Exist");
        }

        return Optional.of(product);
    }

    @Override
    public Product addNewProduct(ProductDto productDto) {
        Product product = convertProductDtoToProduct(productDto);
        return productRepository.save(product);
    }


    @Override
    public Product updateProduct(Long productId, ProductDto productDto) throws NotFoundException {
        Optional<Product> productUpdateToOptional = productRepository.findById(productId);


        if(productUpdateToOptional.isEmpty()) {
            throw new NotFoundException("Product Not Found");
        }

        Product productToUpdate = productUpdateToOptional.get();

        if(productDto.getDescription() != null) {
            productToUpdate.setDescription(productDto.getDescription());
        }

        if(productDto.getImage() != null) {
            productToUpdate.setImageUrl(productDto.getImage());
        }

        if(productDto.getCategory() != null) {
            Category toBePutInProduct = getCategoryToBeInProduct(productDto);
            productToUpdate.setCategory(toBePutInProduct);
        }

        if(productDto.getTitle() != null) {
            productToUpdate.setTitle(productDto.getTitle());
        }

        if(productDto.getPrice() != 0.0) {
            productToUpdate.setPrice(productDto.getPrice());
        }

        return productRepository.save(productToUpdate);
    }

    @Override
    public Product replaceProduct(Long productId, ProductDto productDto) throws NotFoundException {
        Optional<Product> productUpdateToOptional = productRepository.findById(productId);

        if(productUpdateToOptional.isEmpty()) {
            throw new NotFoundException("Product Not Found for put method");
        }

        Product product = productUpdateToOptional.get();

        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        product.setTitle(productDto.getTitle());

        Category toBePutInProduct = new Category();
        toBePutInProduct.setName(productDto.getCategory());
        product.setCategory(toBePutInProduct);

        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long productId) throws NotFoundException {
        Optional<Product> productToDelete = productRepository.findById(productId);
        if(productToDelete.isEmpty()) {
            throw new NotFoundException("Product Not Found");
        }

        productRepository.delete(productToDelete.get());


        return productToDelete.get();
    }
}
