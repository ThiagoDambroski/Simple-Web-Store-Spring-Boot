package com.dambroski.webStoreProject.Category;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	CategoryService service;
	
	
	@GetMapping
	public List<Category> findAllCategory(){
		return service.findAllCategory();
	}
	
	@GetMapping("/name/{name}")
	public List<Category> findCategoryByName(@PathParam("name") String name){
		return service.findByName(name);
	}
	
	@PostMapping
	public Category postCategory(@RequestBody Category category) {
		return service.postCategory(category);
	}
	
	@DeleteMapping("{id}")
	public void deleteByIdCategory(@PathParam("id") Long id) {
		service.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public Category putCategoryById(@PathParam("id") Long id, @RequestBody Category category) {
		return service.putCategoryById(category, id);
	}
}
