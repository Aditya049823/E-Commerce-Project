package com.SuperMarket.ShoppingWebsite.ResponseDto;

import com.SuperMarket.ShoppingWebsite.Enum.ProductCategory;
import com.SuperMarket.ShoppingWebsite.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponseDto {
    private String productname;

    private int price;

    private ProductCategory productCategory;

    private ProductStatus productStatus;
}
