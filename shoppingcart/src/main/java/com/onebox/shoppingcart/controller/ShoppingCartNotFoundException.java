package com.onebox.shoppingcart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ShoppingCartNotFoundException extends RuntimeException {

   private static final Logger log = LoggerFactory.getLogger(ShoppingCartNotFoundException.class);
   
   ShoppingCartNotFoundException(Long id) {
      super("Could not find shopping cart " + id);
      log.error("Could not find shopping cart " + id);
   }
}
