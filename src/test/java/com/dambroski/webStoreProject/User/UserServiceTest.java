package com.dambroski.webStoreProject.User;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.dambroski.webStoreProject.error.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserServiceTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	UserRepository repository;
	
	@Autowired
	UserService service;
	
	Optional<User> user = Optional.of(User.builder().userId((long)1).name("leticia").email("leticia@gmail.com").build());
	
	
	@Test
	public void findUserById() throws Exception {
		Mockito.when(repository.findById(user.get().getUserId())).thenReturn(user);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/get/1")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(user.get().getName())));
	
	}
	
	
	@Test // Exception Test
	public void testUserNotFoundInFindUserById() throws Exception {
		Mockito.when(repository.findById((long) 5)).thenReturn(Optional.empty());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/get/5")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof UserNotFoundException))
				.andExpect(result -> assertEquals(result.getResolvedException().getMessage()
						,"User " + 5 + " Not found"));
	}
	
	@Test
	public void findUserByEmail() throws Exception {
		Mockito.when(repository.findByUserEmail(user.get().getEmail())).thenReturn(user);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/email/leticia@gmail.com")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is(user.get().getEmail())));
	}
	
	@Test
	public void testUserNotFoundInUserByEmail() throws Exception {
		Mockito.when(repository.findByUserEmail("thiago@hotmail.com")).thenReturn(Optional.empty());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/email/thiago@hotmail.com")
						.contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isNotFound())
					.andExpect(result -> assertTrue(result.getResolvedException() instanceof UserNotFoundException))
					.andExpect(result -> assertEquals(result.getResolvedException().getMessage(), 
							"Email " + "thiago@hotmail.com" + " not found"));
		
	}

	
}
