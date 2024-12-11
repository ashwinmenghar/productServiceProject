package dev.ashwin.productservicettsevening.clients.fakeStoreApi;

import dev.ashwin.productservicettsevening.dtos.RatingDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FakeStoreProductDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
    private RatingDto rating;
}
