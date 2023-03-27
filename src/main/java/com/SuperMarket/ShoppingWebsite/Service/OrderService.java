package com.SuperMarket.ShoppingWebsite.Service;

import com.SuperMarket.ShoppingWebsite.Entity.*;
import com.SuperMarket.ShoppingWebsite.Enum.ProductStatus;
import com.SuperMarket.ShoppingWebsite.Exception.CustomerNotFound;
import com.SuperMarket.ShoppingWebsite.Repository.CustomerRepository;
import com.SuperMarket.ShoppingWebsite.Repository.ProductRepository;
import com.SuperMarket.ShoppingWebsite.RequestDto.OrderRequestDto;
import com.SuperMarket.ShoppingWebsite.ResponseDto.OrderResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception{
        Customer customer;
        try{
            customer=customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch(Exception e)
        {
            throw new CustomerNotFound("Invalid customer Id");
        }

        Product product;
        try {
            product=productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e)
        {
            throw new Exception("Product not found");
        }

        if(product.getQuantity()<orderRequestDto.getRequiredQuantity())
        {
            throw  new Exception("Sorry! Required quantity not available");
        }

        Ordered order = new Ordered();
        order.setTotalCost(orderRequestDto.getRequiredQuantity()*product.getPrice());
        order.setDeliveryCharge(40);
        Card card =customer.getCards().get(0);
        String cardNo="";
        for(int i=0;i<card.getCardNo().length()-4;i++)
        {
            cardNo+='X';
        }
        cardNo+=card.getCardNo().substring(card.getCardNo().length()-4);
        order.setCardUsedforPayment(cardNo);

        Item item=new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setOrder(order);
        order.getItemList().add(item);
        order.setCustomer(customer);

        int leftQuantity=product.getQuantity()- orderRequestDto.getRequiredQuantity();
        if(leftQuantity<=0)
        {
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
        product.setQuantity(leftQuantity);

        customer.getOrderedList().add(order);
        Customer savedCustomer=customerRepository.save(customer);
        Ordered saveOrder=savedCustomer.getOrderedList().get(savedCustomer.getOrderedList().size()-1);

        OrderResponseDto orderResponseDto=OrderResponseDto.builder()
                .productName(product.getName())
                .orderDate(saveOrder.getOrderDate())
                .quantityOrdered(orderRequestDto.getRequiredQuantity())
                .cardUsedForPayment(cardNo)
                .itemPrice((product.getPrice()))
                .totalCost(order.getTotalCost())
                .deliveryCharge(40)
                .build();

        return orderResponseDto;
    }
}
