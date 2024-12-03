package dev.anshul.EcomProductService.repository;

import dev.anshul.EcomProductService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
//earlier uuid is integer coz id was int
public interface ProductRepository extends JpaRepository<Product, UUID>{
    Product findProductByTitle(String title);
    Product findFirstProductByTitle(String title);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

}

//write in camel case with attributes name properly, basic methods
// native query will be teach latter, native customer query, complex queries