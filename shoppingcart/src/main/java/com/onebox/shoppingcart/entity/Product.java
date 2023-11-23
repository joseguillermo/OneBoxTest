package com.onebox.shoppingcart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Product {
   
   private @Id @GeneratedValue Long id;
   private String description;
   private int amount;

   public Product() {}

   public Product(String description, int amount) {
      this.description = description;
      this.amount = amount;
   }

   public Long getId() {
      return this.id;
   }  

   public void setId(Long id) {
      this.id = id;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public int getAmount() {
      return this.amount;
   }

   public void setAmount(int amount) {
      this.amount = amount;
   }

   public String toString() {
      return "Product {" + "id=" + this.id + ", description='" + this.description + '\'' + ", amount='" + this.amount + '\'' + '}';
   }
}
