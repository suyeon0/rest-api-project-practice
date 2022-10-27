package com.example.projectpractice.shared.domain.order.repository;

import com.example.projectpractice.shared.domain.order.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

}
