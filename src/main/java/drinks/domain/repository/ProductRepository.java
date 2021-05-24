package drinks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drinks.domain.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsByName(String name);

	boolean existsByNameAndIdNot(String name, Long id);

}
