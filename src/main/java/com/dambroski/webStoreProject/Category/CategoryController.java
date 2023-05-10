package com.dambroski.webStoreProject.Category;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@GetMapping("/getAll")
	public List<Category> findAllCategory(){
		return service.findAllCategory();
	}
	
	@GetMapping("/getById/{categoryId}")
	public Category getCategoryById(@PathVariable("categoryId") Long categoryId) {
		return service.getCategoryById(categoryId);
	}
	
	@GetMapping("/getByName/{name}")
	public List<Category> findCategoryByName(@PathVariable("name") String name){
		return service.findByName(name);
	}
	
	@PostMapping("/post")
	public Category postCategory(@RequestBody Category category) {
		return service.postCategory(category);
	}
	
	@DeleteMapping("deleteById/{id}")
	public void deleteByIdCategory(@PathVariable("id") Long id) {
		service.deleteById(id);
	}
	
	@PutMapping("putCategory/{id}")
	public Category putCategoryById(@PathVariable("id") Long id, @RequestBody Category category) {
		return service.putCategoryById(category, id);
	}
}
