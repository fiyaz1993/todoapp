/**
 * 
 */
package com.eshop.microservices.shoppingbasketservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;

/**
 * @author Avijit Chakraborty
 *
 */
@Entity
@Table(name="shopping_basket")
@Data
public class ShoppingBasket {

	@Id
	@Column(name="basket_Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="CustomerId")
	private Long customerId;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="TotalPrice")
	private Double totalPrice;
	
	@OneToMany(cascade = {CascadeType.REMOVE,
            CascadeType.MERGE,
            CascadeType.PERSIST}, mappedBy="shoppingBasket", fetch=FetchType.EAGER)
//	@JoinTable(name = "SHOPPING_BASKET_ITEM_JOIN", joinColumns = { @JoinColumn(name = "ShoppingBasketId") }, inverseJoinColumns = { @JoinColumn(name = "ShoppingBasketItemId") })
	private List<ShoppingBasketItem> basketItems;
	
}
