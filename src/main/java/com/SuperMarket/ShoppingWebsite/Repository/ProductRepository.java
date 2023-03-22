package com.SuperMarket.ShoppingWebsite.Repository;

import com.SuperMarket.ShoppingWebsite.Entity.Product;
import com.SuperMarket.ShoppingWebsite.Enum.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product>findAllByProductCategory(ProductCategory productCategory);
}
