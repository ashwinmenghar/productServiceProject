package dev.ashwin.productservicettsevening.inheritanceexamples.mappedsuperclass;

import dev.ashwin.productservicettsevening.inheritanceexamples.joinedtable.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MSUserRepository extends JpaRepository<User, Long> {
    User save(User user);
    User findUserById(Long id);
}
