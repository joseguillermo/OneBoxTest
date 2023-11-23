package com.onebox.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onebox.shoppingcart.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
