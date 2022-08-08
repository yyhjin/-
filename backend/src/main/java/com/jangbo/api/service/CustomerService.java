package com.jangbo.api.service;

import com.jangbo.db.entity.Customer;
import com.jangbo.db.repository.CustomerRepositoryTemp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepositoryTemp customerRepository;

    @Transactional
    public Integer join(Customer customer) {
        validateDuplicateCustomer(customer);
        customerRepository.save(customer);
        return customer.getCustomerNo();
    }

    private void validateDuplicateCustomer(Customer seller) {
        //EXCEPTION
        List<Customer> findCustomers = customerRepository.findByCustomerId(seller.getCustomerId());
        if (!findCustomers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
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
