package com.dambroski.webStoreProject.Itens;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import com.dambroski.webStoreProject.Category.Category;
import com.dambroski.webStoreProject.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Item{
	
	@Id
	@SequenceGenerator(name = "item_sequence",sequenceName = "item_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "item_sequence")
	private long itemId;
	private String name;
	private double price;
	private int stock;
	
	@JsonIgnoreProperties({"itens","categoryId"})
	@ManyToMany(mappedBy = "itens")
	private List<Category> category;
	
	@ManyToMany(mappedBy = "whishList")
	@JsonIgnore
	private List<User> user;
	
	
	
	
	
	
	
	

}
