/**
 * 
 */
package com.eshop.microservices.shoppingbasketservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Avijit Chakraborty
 *
 */
@Entity
@Table(name="shopping_basket_items")
@Data
public class ShoppingBasketItem {

	@Id
	@Column(name="Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="ShoppingBasketId")
	private Long shoppingBasketId;
	
	@Column(name="ProductId")
	private Long productId;
	
	@Column(name="Price")
	private Double price;
	
	@ManyToOne
	@JoinColumn(name="basket_Id", nullable=false)
	private ShoppingBasket shoppingBasket;
}
