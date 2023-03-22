package com.SuperMarket.ShoppingWebsite.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int sellerId;

    private String name;

    @Column(unique = true)
    private String email;

    private String mobNo;

    @Column(unique = true)
    private String panNo;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    List<Product>products=new ArrayList<>();
}
