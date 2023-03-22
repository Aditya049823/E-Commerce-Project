package com.SuperMarket.ShoppingWebsite.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerRequestDto {

    private String name;

    private String email;

    private String panNo;

    private String mobNo;
}
