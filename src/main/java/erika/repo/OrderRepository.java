package erika.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import erika.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}