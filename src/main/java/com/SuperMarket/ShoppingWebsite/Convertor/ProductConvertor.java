package com.SuperMarket.ShoppingWebsite.Convertor;

import com.SuperMarket.ShoppingWebsite.Entity.Product;
import com.SuperMarket.ShoppingWebsite.Enum.ProductCategory;
import com.SuperMarket.ShoppingWebsite.Enum.ProductStatus;
import com.SuperMarket.ShoppingWebsite.RequestDto.ProductRequestDto;
import com.SuperMarket.ShoppingWebsite.ResponseDto.ProductResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductConvertor {

    public static Product dtoToProduct(ProductRequestDto productRequestDto)
    {
        return Product.builder().name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productCategory(ProductCategory.SPORTS)
                .productStatus((ProductStatus.AVAILABLE))
                .build();
    }
    public static ProductResponseDto productToDto(Product product)
    {
        return ProductResponseDto.builder().name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
}
