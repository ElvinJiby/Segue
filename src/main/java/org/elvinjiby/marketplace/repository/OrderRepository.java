package org.elvinjiby.marketplace.repository;

import org.elvinjiby.marketplace.model.Order;
import org.elvinjiby.marketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(User customer);
}
