package com.onebox.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ShoppingcartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartApplication.class, args);
	}

}
