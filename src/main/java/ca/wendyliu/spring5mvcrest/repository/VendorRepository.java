package ca.wendyliu.spring5mvcrest.repository;

import ca.wendyliu.spring5mvcrest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

    Optional<Vendor> findVendorById(Long id);
}
