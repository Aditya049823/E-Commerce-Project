package com.SuperMarket.ShoppingWebsite.Controller;

import com.SuperMarket.ShoppingWebsite.RequestDto.CustomerRequestDto;
import com.SuperMarket.ShoppingWebsite.ResponseDto.CustomerResponseDto;
import com.SuperMarket.ShoppingWebsite.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public String addCustomer(@RequestBody CustomerRequestDto customerRequestDto)
    {
        return customerService.addCustomer(customerRequestDto);
    }

    @GetMapping("/get")
    public CustomerResponseDto getCustomerbyId(@RequestParam("id") int id) throws Exception {
        return customerService.getCustomerbyId(id);
    }

    @GetMapping("/getAll")
    public List<CustomerResponseDto> getAllCustomer()
    {
        return customerService.getAllCustomer();
    }
}
