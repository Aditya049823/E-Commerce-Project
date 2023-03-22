package com.SuperMarket.ShoppingWebsite.Service;

import com.SuperMarket.ShoppingWebsite.Convertor.ProductConvertor;
import com.SuperMarket.ShoppingWebsite.Entity.Product;
import com.SuperMarket.ShoppingWebsite.Entity.Seller;
import com.SuperMarket.ShoppingWebsite.Enum.ProductCategory;
import com.SuperMarket.ShoppingWebsite.Exception.SellerNotFoundException;
import com.SuperMarket.ShoppingWebsite.Repository.ProductRepository;
import com.SuperMarket.ShoppingWebsite.Repository.SellerRepository;
import com.SuperMarket.ShoppingWebsite.RequestDto.ProductRequestDto;
import com.SuperMarket.ShoppingWebsite.ResponseDto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {
        Seller seller;
        try {
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        } catch (Exception e) {
            throw new SellerNotFoundException("Invalid Seller Id");
        }
        Product product=ProductConvertor.dtoToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProducts().add(product);
        sellerRepository.save(seller);

        ProductResponseDto productResponseDto=ProductConvertor.productToDto(product);
        return productResponseDto;
    }

    public List<ProductResponseDto> getProductByCategory(ProductCategory productCategory) {
        List<Product>products=productRepository.findAllByProductCategory(productCategory);
        List<ProductResponseDto>productResponseDtos=new ArrayList<>();
        for(Product product:products)
        {
            ProductResponseDto productResponseDto=ProductConvertor.productToDto(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }
}
