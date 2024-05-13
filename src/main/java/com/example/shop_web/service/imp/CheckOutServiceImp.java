package com.example.shop_web.service.imp;

import com.example.shop_web.exception.BaseException;
import com.example.shop_web.model.dto.response.CheckOutResponse;
import com.example.shop_web.model.entity.*;
import com.example.shop_web.repository.AddressRepository;
import com.example.shop_web.repository.OderDetailRepository;
import com.example.shop_web.repository.OderRepository;
import com.example.shop_web.repository.ProductRepository;
import com.example.shop_web.service.CheckOutService;
import com.example.shop_web.service.ShoppingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckOutServiceImp implements CheckOutService {
    ShoppingService shoppingService;
    OderRepository oderRepository;
    OderDetailRepository oderDetailRepository;
    AddressRepository addressRepository;


    @Override
    public CheckOutResponse paymentInfo(Long addressId) {
        UsersEntity user = shoppingService.userUsing();
        AddressEntity address = addressRepository.findByAddressIdAndUsersByUserId(addressId, user);
        if (address == null) {
            throw new BaseException("RA-C21-400");
        }

        // Tạo đơn hàng
        OrdersEntity order = new OrdersEntity();
        order.setUserId(user.getUserId());
        order.setTotalPrice(shoppingService.Payment());
        order.setStatus("Waiting");
        order.setNote("note");
        order.setReceiveName(address.getReceiveName());
        order.setReceiveAddress(address.getFullAddress());
        order.setReceivePhone(address.getPhone());
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        Timestamp createdAt = order.getCreatedAt();
        LocalDateTime receivedAt = LocalDateTime.ofInstant(createdAt.toInstant(), ZoneId.systemDefault()).plusDays(4);
        Timestamp receivedAtTimestamp = Timestamp.valueOf(receivedAt);
        order.setReceiveAt(receivedAtTimestamp);

        OrdersEntity savedOrder = oderRepository.save(order);

        // Lấy danh sách sản phẩm trong giỏ hàng
        List<ShoppingCartEntity> cartItems = shoppingService.getAllListCart();

        // Tạo danh sách chi tiết đơn hàng
        List<OrderDetailsEntity> orderDetailsList = new ArrayList<>();
        for (ShoppingCartEntity cartItem : cartItems) {
            OrderDetailsEntity orderDetail = new OrderDetailsEntity();
            orderDetail.setOrderId(savedOrder.getOrderId());
            orderDetail.setProductId(cartItem.getProductsByProductId().getProductId());
            orderDetail.setName(cartItem.getProductsByProductId().getProductName());
            orderDetail.setUnitPrice(cartItem.getProductsByProductId().getUnitPrice());
            orderDetail.setOrderQuantity(cartItem.getOrderQuantity());
            orderDetailsList.add(orderDetail);
        }
        oderDetailRepository.saveAll(orderDetailsList);


        CheckOutResponse response = new CheckOutResponse();
        response.setSerialNumber(savedOrder.getSerialNumber());
        response.setTotalPrice(savedOrder.getTotalPrice());
        response.setStatus(savedOrder.getStatus());
        response.setNote(savedOrder.getNote());
        response.setReceiveName(savedOrder.getReceiveName());
        response.setReceiveAddress(savedOrder.getReceiveAddress());
        response.setReceivePhone(savedOrder.getReceivePhone());
        response.setCreatedAt(savedOrder.getCreatedAt());
        response.setReceivedAt(savedOrder.getReceiveAt());
        return response;
    }

}
