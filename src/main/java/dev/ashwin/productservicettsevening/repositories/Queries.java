package dev.ashwin.productservicettsevening.repositories;

public interface Queries {
    String LAAO_PRODUCTS_WITH_ID_QUERY = "select title as title from product where id = :id";

}
