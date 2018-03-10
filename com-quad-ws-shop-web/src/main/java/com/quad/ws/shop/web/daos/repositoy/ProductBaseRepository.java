package com.quad.ws.shop.web.daos.repositoy;

import com.quad.ws.shop.web.pojo.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ProductBaseRepository<T extends Product> extends CrudRepository<T,Long> {
    Product findByProductId(int productId);

    Optional<Product> findByName(String name);


    @Query(value = "SELECT * FROM products WHERE CATEGORIES=:category", nativeQuery = true)
    List<Product> findByCategories(@Param("category") String category);
}
