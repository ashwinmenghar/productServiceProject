package dev.ashwin.productservicettsevening.inheritanceexamples.singleclass;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_instructor")
@DiscriminatorValue("2")
public class Instructor extends User {
    private boolean isHandsome;
}
