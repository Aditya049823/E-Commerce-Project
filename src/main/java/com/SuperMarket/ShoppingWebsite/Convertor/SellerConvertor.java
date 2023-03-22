package com.SuperMarket.ShoppingWebsite.Convertor;

import com.SuperMarket.ShoppingWebsite.Entity.Seller;
import com.SuperMarket.ShoppingWebsite.RequestDto.SellerRequestDto;
import com.SuperMarket.ShoppingWebsite.ResponseDto.SellerResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerConvertor {

    public static Seller sellerRequestDtoConvertor(SellerRequestDto sellerRequestDto)
    {
        return Seller.builder().name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .mobNo(sellerRequestDto.getMobNo())
                .panNo(sellerRequestDto.getPanNo())
                .build();
    }

    public static SellerResponseDto sellerToDto(Seller seller)
    {
        return SellerResponseDto.builder()
                .id(seller.getSellerId())
                .name(seller.getName())
                .email(seller.getEmail())
                .mobNo(seller.getMobNo())
                .panNo(seller.getPanNo())
                .build();
    }
}
