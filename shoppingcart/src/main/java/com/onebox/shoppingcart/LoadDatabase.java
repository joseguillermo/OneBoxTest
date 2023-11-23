package com.onebox.shoppingcart;

import com.onebox.shoppingcart.entity.Product;
import com.onebox.shoppingcart.repository.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

   private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

   @Bean
   CommandLineRunner initDatabase(ProductRepository repository) {
      return args -> {
         log.info("Preloading " + repository.save(new Product("Manzanas", 3)));
         log.info("Preloading " + repository.save(new Product("Peras", 2)));
         log.info("Preloading " + repository.save(new Product("Sandias", 1)));
         log.info("Preloading " + repository.save(new Product("Melones", 1)));
         log.info("Preloading " + repository.save(new Product("Papayas", 6)));
         log.info("Preloading " + repository.save(new Product("Fresas", 25)));
      };
   }
}
