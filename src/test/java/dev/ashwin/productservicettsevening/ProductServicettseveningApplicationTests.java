package dev.ashwin.productservicettsevening;


import dev.ashwin.productservicettsevening.inheritanceexamples.joinedtable.JTMentorRepository;
import dev.ashwin.productservicettsevening.inheritanceexamples.joinedtable.JTUserRepository;
import dev.ashwin.productservicettsevening.inheritanceexamples.joinedtable.Mentor;
import dev.ashwin.productservicettsevening.inheritanceexamples.joinedtable.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServicettseveningApplicationTests {

    @Autowired
    private JTUserRepository userRepository;
    @Autowired
    private JTMentorRepository mentorRepository;

    @Test
    void contextLoads() {
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

}
