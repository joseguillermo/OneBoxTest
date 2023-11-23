package com.onebox.shoppingcart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onebox.shoppingcart.entity.Product;
import com.onebox.shoppingcart.entity.ShoppingCart;
import com.onebox.shoppingcart.repository.ProductRepository;
import com.onebox.shoppingcart.repository.ShoppingCartRepository;

@RestController
@EnableScheduling
class ShoppingCartController {

   private final ShoppingCartRepository shoppingCartRepository;
   private final ProductRepository productRepository;
   private static final Logger log = LoggerFactory.getLogger(ShoppingCartController.class);
   private static final int SHOPPING_CART_TIMEOUT = 600000;

   ShoppingCartController(ShoppingCartRepository repository, ProductRepository productRepository) {
      this.shoppingCartRepository = repository;
      this.productRepository = productRepository;
   }

   @PostMapping("/shoppingcart")
   public ShoppingCart createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
      return shoppingCartRepository.save(shoppingCart);
   }

   // Aggregate root
   // tag::get-aggregate-root[]
   @GetMapping("/shoppingcart")
   public List<ShoppingCart> readAll() {
      return shoppingCartRepository.findAll();
   }
   // end::get-aggregate-root[]
  
   @GetMapping("/shoppingcart/{id}")
   public ShoppingCart readShoppingCart(@PathVariable Long id) {
      return shoppingCartRepository.findById(id)
      .orElseThrow(() -> new ShoppingCartNotFoundException(id));
   }

   @PutMapping("/shoppingcart/{shoppingCartId}")
   public ShoppingCart updateShoppingCart(@PathVariable Long shoppingCartId, @RequestBody List<Long> products) {
      return shoppingCartRepository.findById(shoppingCartId)
      .map(shoppingCart -> {
         List<Product> productsToCart = productRepository.findAllById(products);
         List<Product> productsInCart = shoppingCart.getProducts();
         for (Product product : productsToCart) {
            // if there is no product with the same id in the shopping cart, add it
            if (!productsInCart.contains(product)) {
               productsInCart.add(product);
            } else { // otherwise, add the amount of the product to the existing one
               productsInCart.get(productsInCart.indexOf(product))
               .setAmount(product.getAmount() + productsInCart.get(productsInCart.indexOf(product)).getAmount());
            }
            shoppingCart.setModification();
         }
         return shoppingCartRepository.save(shoppingCart);
      })
      .orElseThrow(() -> new ShoppingCartNotFoundException(shoppingCartId));
   }

   @DeleteMapping("/shoppingcart/{id}")
   public void deleteShoppingCart(@PathVariable Long id) {
      shoppingCartRepository.deleteById(id);
   }

   @Scheduled(fixedRate = SHOPPING_CART_TIMEOUT)
   public void deleteOldShoppingCarts() {
      List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
      for (ShoppingCart shoppingCart : shoppingCarts) {
         log.info("deleteOldShoppingCarts cart " + (System.currentTimeMillis() - shoppingCart.getModification().getTime()));
         if ((System.currentTimeMillis() - shoppingCart.getModification().getTime()) > SHOPPING_CART_TIMEOUT) {
            shoppingCartRepository.delete(shoppingCart);
            log.info("Shopping cart " + shoppingCart.getId() + " timed out and was deleted.");
         }
      }
   }
}
