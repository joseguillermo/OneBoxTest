package com.onebox.shoppingcart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ShoppingCartNotFoundAdvice {

   @ResponseBody
   @ExceptionHandler(ShoppingCartNotFoundException.class)
   @ResponseStatus(HttpStatus.NOT_FOUND)
   String shoppingCartNotFoundHandler(ShoppingCartNotFoundException ex) {
      return ex.getMessage();
   }
}
