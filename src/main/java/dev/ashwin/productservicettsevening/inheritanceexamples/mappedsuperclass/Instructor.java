package dev.ashwin.productservicettsevening.inheritanceexamples.mappedsuperclass;

import jakarta.persistence.Entity;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ms_instructor")
public class Instructor extends User {
    private boolean isHandsome;
}
