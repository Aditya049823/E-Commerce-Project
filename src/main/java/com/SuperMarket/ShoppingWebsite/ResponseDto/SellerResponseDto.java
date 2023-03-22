package com.SuperMarket.ShoppingWebsite.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerResponseDto {

    private int id;

    private String name;

    private String email;

    private String mobNo;

    private String panNo;

}
