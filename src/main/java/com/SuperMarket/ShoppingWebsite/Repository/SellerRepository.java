package com.SuperMarket.ShoppingWebsite.Repository;

import com.SuperMarket.ShoppingWebsite.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
     Seller findByPanNo(String pancard);
}
