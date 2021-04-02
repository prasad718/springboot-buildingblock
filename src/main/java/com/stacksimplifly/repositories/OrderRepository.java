package com.stacksimplifly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplifly.entity.Order;


@Repository
public interface OrderRepository  extends JpaRepository<Order, Long>{

}
