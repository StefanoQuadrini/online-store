package com.quad.ws.shop.web.daos;

import com.quad.ws.shop.web.daos.repositoy.ProductBaseRepository;
import com.quad.ws.shop.web.pojo.Product;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductDao extends ProductBaseRepository<Product> {
}
