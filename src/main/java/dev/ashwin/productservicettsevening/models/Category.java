package dev.ashwin.productservicettsevening.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
//@Table(name = "naman")
public class Category extends BaseModel {
    private String name;
    //    @Column
    private String description;

    // C : P
    // 1 : m
    // 1 : 1
    // 1 : m

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = {CascadeType.REMOVE})
//    @Fetch(FetchMode.SELECT)
//    @BatchSize(size = 1)
//    private Set<Product> products;

//    @OneToOne
//    @OneToMany
//    @ManyToOne
//    @ManyToMany

}
