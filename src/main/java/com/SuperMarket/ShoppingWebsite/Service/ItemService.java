package com.SuperMarket.ShoppingWebsite.Service;

import com.SuperMarket.ShoppingWebsite.Entity.Item;
import com.SuperMarket.ShoppingWebsite.Entity.Product;
import com.SuperMarket.ShoppingWebsite.Repository.ProductRepository;
import com.SuperMarket.ShoppingWebsite.ResponseDto.ItemResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ProductRepository productRepository;

    public ItemResponseDto viewItem(int productid) throws Exception
    {
        Product product;
        try {
            product=productRepository.findById(productid).get();
        }
        catch(Exception e)
        {
            throw new Exception("Sorry Invalid product Id");
        }
        Item item= Item.builder()
                .requiredQuantity(0)
                .product(product)
                .build();

        product.setItem(item);
        productRepository.save(product);

        ItemResponseDto itemResponseDto= ItemResponseDto.builder()
                .productname(product.getName())
                .productCategory(product.getProductCategory())
                .price(product.getPrice())
                .productStatus((product.getProductStatus()))
                .build();

        return itemResponseDto;
    }
}
