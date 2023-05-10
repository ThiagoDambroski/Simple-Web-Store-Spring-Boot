package com.dambroski.webStoreProject.Category;

import java.util.List;

public interface CategoryService {

	List<Category> findAllCategory();

	List<Category> findByName(String name);

	Category postCategory(Category category);

	void deleteById(Long id);

	Category putCategoryById(Category category, Long id);

	Category getCategoryById(Long categoryId);

	
}
