package dev.ashwin.productservicettsevening;


import dev.ashwin.productservicettsevening.inheritanceexamples.joinedtable.JTMentorRepository;
import dev.ashwin.productservicettsevening.inheritanceexamples.joinedtable.JTUserRepository;
import dev.ashwin.productservicettsevening.inheritanceexamples.joinedtable.Mentor;
import dev.ashwin.productservicettsevening.inheritanceexamples.joinedtable.User;
import dev.ashwin.productservicettsevening.models.Category;
import dev.ashwin.productservicettsevening.models.Product;
import dev.ashwin.productservicettsevening.repositories.CategoryRepository;
import dev.ashwin.productservicettsevening.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class ProductServicettseveningApplicationTests {

    @Autowired
    private JTUserRepository userRepository;
    @Autowired
    private JTMentorRepository mentorRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    void printName() {
    }

//    @Test
//    void testDifferentInheritances() {
//
//        User user = new User();
//        user.setEmail("joined@gmail.com");
//        user.setPassword("asda");
//
//        Mentor mentor = new Mentor();
//        mentor.setEmail("ms@gmail.com");
//        mentor.setPassword("asd");
//        mentor.setNumberOfMentees(12);
//        mentor.setNumberOfSessions(22);
//
//        mentorRepository.save(mentor);
//    }

    @Test
    @Rollback(false)
    void generateProduct() {
        Category category = new Category();
        category.setName("Human");

       Category saveCategory =  categoryRepository.save(category);

        Product product = new Product();
        product.setCategory(saveCategory);
        product.setTitle("Ashwin");
        product.setDescription("Ashwin is product ");
        product.setImageUrl("asdasd");
        product.setPrice(101.1);
        productRepository.save(product);
    }

}
