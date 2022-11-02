package com.dambroski.webStoreProject.Itens;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
	

	@Query("select i from Item i where i.name LIKE '%'||:name||'%'")
	 List<Item> findByNameLike(@Param("name") String name);

}
