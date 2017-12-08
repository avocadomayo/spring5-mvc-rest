package ca.wendyliu.spring5mvcrest.repository;

import ca.wendyliu.spring5mvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Auto-implemented by Spring
    Optional<Customer> findById(Long id);
}
