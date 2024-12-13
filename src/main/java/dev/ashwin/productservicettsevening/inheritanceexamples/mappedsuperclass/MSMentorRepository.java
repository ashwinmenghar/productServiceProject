package dev.ashwin.productservicettsevening.inheritanceexamples.mappedsuperclass;

import dev.ashwin.productservicettsevening.inheritanceexamples.mappedsuperclass.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MSMentorRepository  extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor mentor);
    Mentor findMentorById(Long id);
}
