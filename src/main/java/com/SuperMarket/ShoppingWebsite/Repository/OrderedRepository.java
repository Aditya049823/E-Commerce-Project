package com.SuperMarket.ShoppingWebsite.Repository;

import com.SuperMarket.ShoppingWebsite.Entity.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered,Integer> {
}
