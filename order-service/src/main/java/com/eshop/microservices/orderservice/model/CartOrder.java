/**
 * 
 */
package com.eshop.microservices.orderservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * @author Avijit Chakraborty
 *
 */
@Data
@Entity
public class CartOrder {

	@Id
	@GeneratedValue
	@Column(name="Id")
	private Integer id;
	
	private LocalDateTime date;
	
	private Double amount;
	
	private String user;
}
