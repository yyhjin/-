package com.jangbo.api.service.Auth;

import com.jangbo.api.request.CustomerRegisterReq;
import com.jangbo.api.request.SellerRegisterReq;
import com.jangbo.db.entity.Customer;
import com.jangbo.db.entity.Seller;

public interface AuthService {
    void signUpSeller(SellerRegisterReq sellerRegisterReq);

    void signUpCustomer(CustomerRegisterReq customerRegisterReq);

    Seller loginSeller(String sellerId, String sellerPwd) throws Exception;

    Customer loginCustomer(String customerId, String customerPwd) throws Exception;


    void changeSellerPassword(Seller seller, String sellerPwd, String sellerPwdUpdate) throws Exception;

    void changeCustomerPassword(Customer customer, String customerPwd, String customerPwdUpdate) throws Exception;

//    Seller findBySellerId(String sellerId) throws Exception;

//    Customer findByCustomerId(String customerId) throws Exception;

    void logout(String accessToken, String refreshToken);
}