package com.dambroski.webStoreProject.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dambroski.webStoreProject.Itens.Item;

public interface CategoryRepository extends JpaRepository<Category, Long> {
		
	@Query("select c from Category c where c.name LIKE '%'||:name||'%'")
	List<Category> getCategoryByName(@Param("name")String name);
	
	
	
}
