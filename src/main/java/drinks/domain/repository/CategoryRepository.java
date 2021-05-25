package drinks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drinks.domain.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	boolean existsByName(String name);

	boolean existsByNameAndIdNot(String name, Long id);

}
