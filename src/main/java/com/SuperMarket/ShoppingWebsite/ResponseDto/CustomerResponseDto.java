package com.SuperMarket.ShoppingWebsite.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDto {
    private  int id;

    private String name;

    private int age;

    private int mobNo;

    private String email;
}
