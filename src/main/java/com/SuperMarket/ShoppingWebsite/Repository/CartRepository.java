package com.SuperMarket.ShoppingWebsite.Repository;

import com.SuperMarket.ShoppingWebsite.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
}
