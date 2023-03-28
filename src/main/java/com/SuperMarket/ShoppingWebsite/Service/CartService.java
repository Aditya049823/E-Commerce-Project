package com.SuperMarket.ShoppingWebsite.Service;

import com.SuperMarket.ShoppingWebsite.Entity.*;
import com.SuperMarket.ShoppingWebsite.Enum.ProductStatus;
import com.SuperMarket.ShoppingWebsite.Exception.CustomerNotFound;
import com.SuperMarket.ShoppingWebsite.Repository.CustomerRepository;
import com.SuperMarket.ShoppingWebsite.Repository.ProductRepository;
import com.SuperMarket.ShoppingWebsite.RequestDto.OrderRequestDto;
import com.SuperMarket.ShoppingWebsite.ResponseDto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderService orderService;

    public String addToCart(OrderRequestDto orderRequestDto) throws Exception{
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

        Cart cart=customer.getCart();
        int newCost=cart.getCartTotal()+orderRequestDto.getRequiredQuantity()* product.getPrice();
        cart.setCartTotal(newCost);

        Item item=new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);
        cart.getItems().add(item);

        customerRepository.save(customer);

        return "Item has been added to your Cart";
    }
    public List<OrderResponseDto> checkOut(int customerId) throws Exception
    {
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch(Exception e){
            throw new CustomerNotFound("Invalid Customer id !!!");
        }

        List<OrderResponseDto>orderResponseDtos=new ArrayList<>();
        int totalCost=customer.getCart().getCartTotal();
        Cart cart=customer.getCart();
        for(Item item: cart.getItems())
        {
            Ordered order=new Ordered();
            order.setTotalCost(item.getRequiredQuantity()*item.getProduct().getPrice());
            order.setDeliveryCharge(0);
            order.setCustomer(customer);
            order.getItemList().add(item);

            Card card = customer.getCards().get(0);
            String cardNo = "";
            for(int i=0;i<card.getCardNo().length()-4;i++)
                cardNo += 'X';
            cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
            order.setCardUsedforPayment(cardNo);

            int leftQuantity = item.getProduct().getQuantity()-item.getRequiredQuantity();
            if(leftQuantity<=0)
                item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
            item.getProduct().setQuantity(leftQuantity);

            customer.getOrderedList().add(order);

            OrderResponseDto orderResponseDto=OrderResponseDto.builder()
                    .productName(item.getProduct().getName())
                    .orderDate(order.getOrderDate())
                    .quantityOrdered(item.getRequiredQuantity())
                    .cardUsedForPayment(cardNo)
                    .itemPrice(item.getProduct().getPrice())
                    .totalCost(order.getTotalCost())
                    .deliveryCharge(0)
                    .build();

            orderResponseDtos.add(orderResponseDto);
        }
        cart.setItems(new ArrayList<>());
        cart.setCartTotal(0);
        customerRepository.save(customer);
        return orderResponseDtos;
    }
}
