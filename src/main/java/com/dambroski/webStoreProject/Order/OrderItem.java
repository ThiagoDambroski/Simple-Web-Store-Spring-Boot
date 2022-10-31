package com.dambroski.webStoreProject.Order;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.dambroski.webStoreProject.Itens.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
	
	
	@Id
	@SequenceGenerator(name = "order_item_sequence",sequenceName = "order_item_sequence",allocationSize = 1)
	@GeneratedValue(generator = "order_item_sequence",strategy = GenerationType.SEQUENCE)
	private long OrderItemId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itemId", referencedColumnName = "itemId")
	private Item item;
	private int quantity;
	
	

	public double getTotal(){
		return item.getPrice() * quantity;
	};

}
