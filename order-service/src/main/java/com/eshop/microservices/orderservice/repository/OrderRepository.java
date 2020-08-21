package com.eshop.microservices.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.microservices.orderservice.model.CartOrder;

public interface OrderRepository extends JpaRepository<CartOrder, Integer> {

}
