package com.jangbo.api.service;

import com.jangbo.api.request.ItemRegisterPostReq;
import com.jangbo.db.dto.OrderItemDto;
import com.jangbo.db.dto.OrdersDto;
import com.jangbo.db.entity.Item;
import com.jangbo.db.entity.OrderItem;
import com.jangbo.db.entity.Orders;
import com.jangbo.db.entity.Store;
import com.jangbo.db.repository.ItemRepository;
import com.jangbo.db.repository.OrderItemRepository;
import com.jangbo.db.repository.OrdersRepository;
import com.jangbo.db.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;

    public OrderItem orderitemsave(OrderItem orderItem) { return orderItemRepository.save(orderItem); }


}
