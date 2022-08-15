package com.jangbo.db.repository;

import com.jangbo.db.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryTemp {

    private final EntityManager em;

    public void save(Customer customer) {
        em.persist(customer);
    }

    public Customer findOne(Integer customerNo)  {
        return em.find(Customer.class, customerNo);
    }

    public List<Customer> findByCustomerId(String customerId) {
        //parameter name binding .setParameter("name1", name) -> :name1
        return em.createQuery("select c from Customer c where c.customerId = :customerId", Customer.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }


}

