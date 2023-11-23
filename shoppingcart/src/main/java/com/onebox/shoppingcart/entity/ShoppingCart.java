package com.onebox.shoppingcart.entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Entity
public class ShoppingCart {
   
   private @Id @GeneratedValue Long id;
   @OneToMany(cascade = {CascadeType.REMOVE})
   private List<Product> products;
   
   @CreationTimestamp
   private Timestamp creation;
   @UpdateTimestamp
   private Timestamp modification;

   public ShoppingCart() {
      products = new ArrayList<Product>();
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public List<Product> getProducts() {
      return this.products;
   }
   
   public void setProducts(List<Product> products) {
      this.products = products;
   }
   
   public Timestamp getCreation() {
      return this.creation;
   }
   
   public void setCreation() {
      this.creation = Timestamp.from(Instant.now());
   }

   public void setCreation(Timestamp creation) {
      this.creation = creation;
   }
   
   public Timestamp getModification() {
      return this.modification;
   }

   public void setModification() {
      this.creation = Timestamp.from(Instant.now());;
   }
   
   public void setModification(Timestamp modification) {
      this.modification = modification;
   }

   public String toString() {
      return "ShoppingCart {" + "id=" + this.id + ", products='" + this.products + '\'' + ", creation='" + this.creation + '\'' + ", modification='" + this.modification + '\'' + '}';
   }
}
