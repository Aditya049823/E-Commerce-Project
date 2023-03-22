package com.SuperMarket.ShoppingWebsite.Service;

import com.SuperMarket.ShoppingWebsite.Convertor.SellerConvertor;
import com.SuperMarket.ShoppingWebsite.Entity.Seller;
import com.SuperMarket.ShoppingWebsite.Repository.SellerRepository;
import com.SuperMarket.ShoppingWebsite.RequestDto.SellerRequestDto;
import com.SuperMarket.ShoppingWebsite.ResponseDto.SellerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public String addSeller(SellerRequestDto sellerRequestDto)
    {
        Seller seller= SellerConvertor.sellerRequestDtoConvertor(sellerRequestDto);
        sellerRepository.save(seller);
        return "Seller aded Sucessfully";

    }
    public List<SellerResponseDto> getAllsellers()
    {
        List<Seller>sellers=sellerRepository.findAll();
        List<SellerResponseDto>sellerResponseDtos=new ArrayList<>();
        for(Seller seller:sellers)
        {
            SellerResponseDto sellerResponseDto=SellerConvertor.sellerToDto(seller);
            sellerResponseDtos.add(sellerResponseDto);
        }
        return sellerResponseDtos;
    }
}
