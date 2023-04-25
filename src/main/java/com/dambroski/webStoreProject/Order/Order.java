package com.dambroski.webStoreProject.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.dambroski.webStoreProject.OrderItem.OrderItem;
import com.dambroski.webStoreProject.User.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
	
	
	@Id
	@SequenceGenerator(name = "oder_sequence",sequenceName = "order_sequence",allocationSize = 1)
	@GeneratedValue(generator = "order_sequence",strategy = GenerationType.IDENTITY)
	private Long orderId;
	

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "userId",updatable = true)
	private User user;
	
	
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_itens_map",joinColumns = @JoinColumn(
			name = "order_id", referencedColumnName = "orderId"), inverseJoinColumns = @JoinColumn(
					name = "order_item_id",referencedColumnName = "orderItemId",updatable = true))
	private Set<OrderItem> itens;
	
	
	@ElementCollection(targetClass=Long.class)
	@JsonFormat (with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<Long> idItens;
	
	private OrderStatus status;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	
	@Transient
	public double getTotal() {
		double total = 0;
		for (OrderItem item : itens) {
			total += item.getTotal();	
		}
		return total;
	}
	@Override
	 public Object clone() throws CloneNotSupportedException {
	 return super.clone();
	 }

}
