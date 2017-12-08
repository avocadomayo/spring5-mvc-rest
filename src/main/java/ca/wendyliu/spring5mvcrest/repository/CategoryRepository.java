package ca.wendyliu.spring5mvcrest.repository;

import ca.wendyliu.spring5mvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// Has paging and sorting functionality in addition to CRUD
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
}
