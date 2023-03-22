package com.SuperMarket.ShoppingWebsite.Controller;

import com.SuperMarket.ShoppingWebsite.RequestDto.SellerRequestDto;
import com.SuperMarket.ShoppingWebsite.ResponseDto.SellerResponseDto;
import com.SuperMarket.ShoppingWebsite.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public String addUser(@RequestBody SellerRequestDto sellerRequestDto)
    {
        return sellerService.addSeller(sellerRequestDto);
    }

    @GetMapping("/getAll")
    public List<SellerResponseDto> getAllSelers()
    {
        return sellerService.getAllsellers();
    }
}
