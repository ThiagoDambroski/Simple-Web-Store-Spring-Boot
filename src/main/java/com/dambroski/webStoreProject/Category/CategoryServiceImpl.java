package com.dambroski.webStoreProject.Category;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dambroski.webStoreProject.error.CategoryNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository repository;
	
	

	@Override
	public List<Category> findAllCategory() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	@Override
	public Category getCategoryById(Long categoryId) {
		Category category = repository.findById(categoryId)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found"));
		return category;
	}

	@Override
	public List<Category> findByName(String name) {
		
		return repository.getCategoryByName(name);
		
	}

	@Override
	public Category postCategory(Category category) {
		return repository.save(category);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Category putCategoryById(Category category, Long id) {
		
		Category newCategory = repository.findById(id).get();
		
		if(Objects.nonNull(category.getName()) && !"".equals(category.getName())) {
			newCategory.setName(category.getName());
		}
		
		
		
		return repository.save(newCategory);
	}

	

}
