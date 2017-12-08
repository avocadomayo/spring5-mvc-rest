package ca.wendyliu.spring5mvcrest.bootstrap;

import ca.wendyliu.spring5mvcrest.domain.Category;
import ca.wendyliu.spring5mvcrest.domain.Customer;
import ca.wendyliu.spring5mvcrest.domain.Vendor;
import ca.wendyliu.spring5mvcrest.repository.CategoryRepository;
import ca.wendyliu.spring5mvcrest.repository.CustomerRepository;
import ca.wendyliu.spring5mvcrest.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// CommandLineRunner is Spring Boot-specific. Any arguments passed to the JVM will be passed to run().
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    @Autowired
    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository,
                     VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }


    @Override
    public void run(String... strings) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Naj", "Sham"));
        customers.add(new Customer("Bruce", "Li"));
        customers.add(new Customer("Zahra", "MF"));

        customerRepository.save(customers);
        System.out.println("Data loaded = " + customerRepository.count());
    }

    private void loadCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Fruits"));
        categories.add(new Category("Dried"));
        categories.add(new Category("Fresh"));
        categories.add(new Category("Exotic"));
        categories.add(new Category("Nuts"));

        categoryRepository.save(categories);
        System.out.println("Data loaded = " + categoryRepository.count());
    }

    private void loadVendors() {
        List<Vendor> vendors = new ArrayList<>();
        vendors.add(new Vendor("Western Tasty Fruits Ltd."));
        vendors.add(new Vendor("Exotic Fruits Company"));
        vendors.add(new Vendor("Home Fruits"));
        vendors.add(new Vendor("Fun Fresh Fruits Ltd."));
        vendors.add(new Vendor("Nuts for Nuts Company"));
        vendors.add(new Vendor("Tills Laden"));

        vendorRepository.save(vendors);
        System.out.println("Data loaded = " + vendorRepository.count());
    }
}
