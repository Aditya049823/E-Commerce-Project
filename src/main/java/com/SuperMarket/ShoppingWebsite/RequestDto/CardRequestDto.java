package com.SuperMarket.ShoppingWebsite.RequestDto;

import com.SuperMarket.ShoppingWebsite.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardRequestDto {

    private int customerid;

    private String cardNo;

    private int cvv;

    private CardType cardType;
}
