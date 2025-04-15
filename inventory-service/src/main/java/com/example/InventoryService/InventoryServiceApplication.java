package com.example.InventoryService;

import com.example.InventoryService.model.Inventory;
import com.example.InventoryService.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(50);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("iphone_12");
			inventory2.setQuantity(0);
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory2);
		};
	}
}
