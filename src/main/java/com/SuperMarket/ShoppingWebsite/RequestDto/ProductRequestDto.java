package com.SuperMarket.ShoppingWebsite.RequestDto;

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
public class ProductRequestDto {

    private int sellerId;

    private String name;

    private int price;

    private int quantity;

    private ProductCategory productCategory;

}
