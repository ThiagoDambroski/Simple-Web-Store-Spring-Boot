package com.dambroski.webStoreProject.Category;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import com.dambroski.webStoreProject.Itens.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
	
	@Id
	@GeneratedValue(generator = "category_sequence",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "category_sequence",sequenceName = "category_sequence",allocationSize = 1)
	private long categoryId;
	
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "category_item_map",joinColumns = @JoinColumn(
			name = "category_id", 
			referencedColumnName = "categoryId"),
	inverseJoinColumns = @JoinColumn(
					name = "item_id",
					referencedColumnName = "itemId"))
	private List<Item> itens;
	

}
