package com.SuperMarket.ShoppingWebsite.Convertor;

import com.SuperMarket.ShoppingWebsite.Entity.Customer;
import com.SuperMarket.ShoppingWebsite.RequestDto.CustomerRequestDto;
import com.SuperMarket.ShoppingWebsite.ResponseDto.CustomerResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConvertor {

    public static Customer customerRequestDtotoCustomer(CustomerRequestDto customerRequestDto)
    {
        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .email(customerRequestDto.getEmail())
                .mobNo(customerRequestDto.getMobNo())
                .build();
    }

    public static CustomerResponseDto customerToDto(Customer customer)
    {
        return CustomerResponseDto.builder()
                .id(customer.getCustomerId())
                .name(customer.getName())
                .age(customer.getAge())
                .mobNo(customer.getMobNo())
                .email(customer.getEmail())
                .build();
    }
}
