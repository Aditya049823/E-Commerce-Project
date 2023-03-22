package com.SuperMarket.ShoppingWebsite.Service;

import com.SuperMarket.ShoppingWebsite.Entity.Card;
import com.SuperMarket.ShoppingWebsite.Entity.Customer;
import com.SuperMarket.ShoppingWebsite.Exception.CustomerNotFound;
import com.SuperMarket.ShoppingWebsite.Repository.CustomerRepository;
import com.SuperMarket.ShoppingWebsite.RequestDto.CardRequestDto;
import com.SuperMarket.ShoppingWebsite.ResponseDto.CardDto;
import com.SuperMarket.ShoppingWebsite.ResponseDto.CardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    @Autowired
    CustomerRepository customerRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFound {
        Customer customer;
        try {
            customer=customerRepository.findById(cardRequestDto.getCustomerid()).get();
        }
        catch (Exception e)
        {
            throw new CustomerNotFound("Customer id is invalid");
        }

        Card card=Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .customer(customer)
                .build();

        customer.getCards().add(card);
        customerRepository.save(customer);

        CardResponseDto cardResponseDto=new CardResponseDto();
        cardResponseDto.setName(customer.getName());

        List<CardDto>cardDtos=new ArrayList<>();
        for(Card card1: customer.getCards())
        {
            CardDto cardDto=new CardDto();
            cardDto.setCardNo(card1.getCardNo());
            cardDto.setCardType(card1.getCardType());
            cardDtos.add(cardDto);
        }
        cardResponseDto.setCardDtos(cardDtos);
        return cardResponseDto;
    }
}
