package com.quad.ws.shop.web.services;

import com.quad.ws.shop.web.error.exception.CategoryNotFounException;
import com.quad.ws.shop.web.error.exception.ResourceNotFoundException;
import com.quad.ws.shop.web.pojo.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product getProductById(Long productId) throws ResourceNotFoundException;

    List<Product> getProductsByCategory(String category) throws CategoryNotFounException;
}
