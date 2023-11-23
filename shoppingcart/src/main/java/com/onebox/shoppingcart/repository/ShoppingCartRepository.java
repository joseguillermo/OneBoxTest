package com.onebox.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onebox.shoppingcart.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
