package com.jangbo.api.service;

import com.jangbo.db.entity.Customer;
import com.jangbo.db.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer findByCustomerId(String customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    public Customer findOne(Integer customerNo) {
        return customerRepository.findOne(customerNo);
    }

    @Transactional
    public boolean update(
            Integer customerNo,
            String customerName,
            String customerNickname,
            String customerAddr,
            String customerPhone
    ) {
        Customer customer = customerRepository.findOne(customerNo);
        customer.setCustomerName(customerName);
        customer.setCustomerNickname(customerNickname);
        customer.setCustomerAddr(customerAddr);
        customer.setCustomerPhone(customerPhone);
        return true;
    }

}
