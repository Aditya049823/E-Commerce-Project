package com.SuperMarket.ShoppingWebsite.Service;

import com.SuperMarket.ShoppingWebsite.Convertor.CustomerConvertor;
import com.SuperMarket.ShoppingWebsite.Entity.Cart;
import com.SuperMarket.ShoppingWebsite.Entity.Customer;
import com.SuperMarket.ShoppingWebsite.Repository.CustomerRepository;
import com.SuperMarket.ShoppingWebsite.RequestDto.CustomerRequestDto;
import com.SuperMarket.ShoppingWebsite.ResponseDto.CustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public String addCustomer(CustomerRequestDto customerRequestDto)
    {
        Customer customer= CustomerConvertor.customerRequestDtotoCustomer(customerRequestDto);

        Cart cart=new Cart();
        cart.setCustomer(customer);
        cart.setCartTotal(0);

        customer.setCart(cart);

        customerRepository.save(customer);
        return "New Customer added Sucessfully";
    }

    public CustomerResponseDto getCustomerbyId(int id) throws Exception
    {
        Customer customer;
        try
        {
            customer=customerRepository.findById(id).get();
        }
        catch (Exception e)
        {
            throw new Exception("Customer id is Invalid");
        }

        CustomerResponseDto customerResponseDto=CustomerConvertor.customerToDto(customer);
        return customerResponseDto;
    }

    public List<CustomerResponseDto> getAllCustomer()
    {
        List<Customer>customers=customerRepository.findAll();
        List<CustomerResponseDto> customerResponseDtos=new ArrayList<>();
        for(Customer customer:customers)
        {
            CustomerResponseDto customerResponseDto=CustomerConvertor.customerToDto(customer);
            customerResponseDtos.add(customerResponseDto);
        }
        return customerResponseDtos;
    }
}
