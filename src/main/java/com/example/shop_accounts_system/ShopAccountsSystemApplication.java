package com.example.shop_accounts_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ShopAccountsSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopAccountsSystemApplication.class, args);
	}

}
