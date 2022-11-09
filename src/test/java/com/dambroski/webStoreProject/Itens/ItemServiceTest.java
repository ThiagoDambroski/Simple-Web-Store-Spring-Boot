package com.dambroski.webStoreProject.Itens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.notNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.InvalidApplicationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;

import com.dambroski.webStoreProject.Itens.Item.ItemBuilder;
import com.dambroski.webStoreProject.error.ItemNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ItemServiceTest {
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;

	@MockBean
	ItemRepository repository;

	@Autowired
	ItemService service;
	
	

	Optional<Item> item = Optional.of(Item.builder().itemId(1).name("book").price(20).stock(10).build());
	Optional<Item> item2 = Optional.of(Item.builder().itemId(2).name("book about life").price(10).stock(5).build());
	Optional<Item> item3 = Optional.of(Item.builder().itemId(3).name("pen").price(15).stock(40).build());
	

	@Test
	public void findItemByItemName() throws Exception {
		List<Item> list = new ArrayList<>();
		list.add(item.get());
		list.add(item2.get());
		Mockito.when(repository.findByNameLike(item.get().getName())).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/item/name/book")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("book")));

	}
	
	
	@Test 	//Exception Test
	public void testItemNotFoundExceptionInItemByName() throws Exception {
		List<Item> list = new ArrayList<>();
		Mockito.when(repository.findByNameLike("name")).thenReturn(list);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/item/name/name")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ItemNotFoundException))
				.andExpect(result -> assertEquals("Item name not found", result.getResolvedException().getMessage()));
		
		
	}
	

	@Test 
	public void findItemById() throws Exception {
		Mockito.when(repository.findById(item.get().getItemId())).thenReturn(item);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/item/get/1")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.notNullValue()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("book")));

	}
	
	@Test 	//Exception Test
	public void testItemNotFoundExceptionInItemByID() throws Exception {
		Mockito.when(repository.findById((long) 5)).thenReturn(Optional.empty());
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/item/get/5")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof ItemNotFoundException))
		.andExpect(result -> assertEquals("Itemid 5 not found", result.getResolvedException().getMessage()));
		
	}
	
	@Test
	public void postItem() throws Exception {
		Item newItem = Item.builder().name("mouse").price(23).stock(23).build();
		
		Mockito.when(repository.save(newItem)).thenReturn(newItem);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/item/post")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(newItem));
		
		mockMvc.perform(mockRequest)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.notNullValue()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("mouse")));
		
		
	}
	
	
	

	
	@Test
	public void deleteItemById() throws Exception {
		Mockito.when(repository.findById(item.get().getItemId())).thenReturn(item);
		mockMvc.perform(MockMvcRequestBuilders
				.delete(("/api/item/delete/1"))
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test //Exception Test
	public void testItemNotFoundExceptionInDeleteById() throws Exception {
		Mockito.when(repository.findById((long)5)).thenReturn(Optional.empty());
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/api/item/delete/5")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof ItemNotFoundException))
		.andExpect(result -> assertEquals("Item 5 not found", result.getResolvedException().getMessage()));
		
		
	}
	
	
	@Test
	public void putItem() throws Exception {
		Item updateItem = Item.builder().itemId(1).name("controller").price(60).stock(10).build();
		Mockito.when(repository.findById(item.get().getItemId())).thenReturn(item);
		Mockito.when(repository.save(updateItem)).thenReturn(updateItem);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/item/post")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsBytes(updateItem));
		
		mockMvc.perform(mockRequest)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.notNullValue()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("controller")));
	}
	

	@Test //Exception Test
	public void testeItemNotFoundInPut() throws Exception {
		Optional<Item> updateItem = Optional.of(Item.builder().itemId(6).name("controller").price(12).stock(6).build());
		
		Mockito.when(repository.findById(updateItem.get().getItemId())).thenReturn(Optional.empty());
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/item/put/6")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(getArticleInJson(6, updateItem.get()));
		
		
		mockMvc.perform(mockRequest)
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ItemNotFoundException))
				.andExpect(result -> assertEquals("Item " + 6 + " not found", result.getResolvedException().getMessage()));
	}
	
	private String getArticleInJson(long id, Item content) {
        return "{\"itemId\":\"" + id + "\", \"content\":\"" + content + "\"}";
    }
	
	
}
