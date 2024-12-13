package dev.ashwin.productservicettsevening.inheritanceexamples.singleclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SCUserRepository extends JpaRepository<Mentor, Long> {
    User save(User user);
    User findUserById(Long id);
}
