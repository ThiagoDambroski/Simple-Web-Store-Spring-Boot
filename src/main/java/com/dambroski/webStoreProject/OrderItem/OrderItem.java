package com.dambroski.webStoreProject.OrderItem;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@SequenceGenerator(name = "order_item_sequence",sequenceName = "order_item_sequence",allocationSize = 1)
	@GeneratedValue(generator = "order_item_sequence",strategy = GenerationType.SEQUENCE)
	private long OrderItemId;
	
	
	private long idItem;
	
	
	@ManyToOne
	@JoinColumn(name = "item_id",updatable = true)
	private Item item;
	
	
	private int quantity;
	
	

	public double getTotal(){
		return item.getPrice() * quantity;
	};

}
