package com.example.MicroServiceConcept;

import com.example.MicroServiceConcept.dto.ProductRequest;
import com.example.MicroServiceConcept.repository.ProductRespository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class MicroServiceConceptApplicationTests {
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ProductRespository productRespository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistrar) {
		dynamicPropertyRegistrar.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString)
		).andExpect(status().isCreated());
		Assertions.assertTrue(productRespository.findAll().size() == 1);
	}

	@Test
	void shouldGetAllProducts() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
				.andExpect(status().isOk());

	}

	private ProductRequest getProductRequest(){
		return ProductRequest.builder()
				.name("iPhone 14")
				.description("iPhone 14 luss")
				.price(BigDecimal.valueOf(1200))
				.build();
	}


}
