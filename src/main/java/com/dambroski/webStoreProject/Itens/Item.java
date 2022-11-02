package com.dambroski.webStoreProject.Itens;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

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
	private long id;
	private String name;
	private double price;
	private int stock;
	
	
	
	
	
	
	
	

}
