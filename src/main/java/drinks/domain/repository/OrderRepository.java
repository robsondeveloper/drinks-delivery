package drinks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drinks.domain.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
