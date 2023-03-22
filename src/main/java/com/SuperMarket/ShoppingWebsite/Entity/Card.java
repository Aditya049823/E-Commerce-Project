package com.SuperMarket.ShoppingWebsite.Entity;

import com.SuperMarket.ShoppingWebsite.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;

    @Column(unique = true)
    private String cardNo;

    private int cvv;

    private CardType cardType;

    @ManyToOne
    @JoinColumn
    Customer customer;
}
