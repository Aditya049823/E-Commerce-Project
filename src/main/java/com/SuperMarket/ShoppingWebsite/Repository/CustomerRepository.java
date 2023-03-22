package com.SuperMarket.ShoppingWebsite.Repository;

import com.SuperMarket.ShoppingWebsite.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByEmail(String Email);
}
