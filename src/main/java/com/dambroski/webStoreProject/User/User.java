package com.dambroski.webStoreProject.User;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
public class User {
	
	@Id
	@SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "user_sequence")
	private Long userId;
	private String name;
	@NotBlank(message = "please insert a email")
	@Email
	private String email;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_item_map",joinColumns = @JoinColumn(
			name = "user_id",
			referencedColumnName = "userId"),inverseJoinColumns = @JoinColumn(
					name = "item_id",
					referencedColumnName = "itemId"))
	private List<Item> whishList;
	

}
