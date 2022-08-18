package com.jangbo.api.service.Auth;

import com.jangbo.api.request.CustomerRegisterReq;
import com.jangbo.api.request.SellerRegisterReq;
import com.jangbo.db.entity.Customer;
import com.jangbo.db.entity.Salt;
import com.jangbo.db.entity.Seller;
import com.jangbo.db.repository.CustomerRepository;
import com.jangbo.db.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final SellerRepository sellerRepository;
    private final CustomerRepository customerRepository;

    private final SaltUtil saltUtil;

    private final RedisUtil redisUtil;
    private final JwtUtil jwtUtil;

    //판매자 - 회원가입
    @Override
    public void signUpSeller(SellerRegisterReq sellerRegisterReq){
        Seller seller = new Seller();
        seller.setSellerId(sellerRegisterReq.getSellerId());
        seller.setSellerName(sellerRegisterReq.getSellerName());
        seller.setSellerPhone(sellerRegisterReq.getSellerPhone());
        seller.setBusinessNumber(sellerRegisterReq.getBusinessNumber());

        String password = sellerRegisterReq.getSellerPwd();
        String salt = saltUtil.genSalt();
        seller.setSalt(new Salt(salt));
        seller.setSellerPwd(saltUtil.encodePassword(salt, password));
        sellerRepository.save(seller);
    }


    //소비자 - 회원가입
    @Override
    public void signUpCustomer(CustomerRegisterReq customerRegisterReq){
        Customer customer = new Customer();

        customer.setCustomerId(customerRegisterReq.getCustomerId());
        customer.setCustomerName(customerRegisterReq.getCustomerName());
        customer.setCustomerNickname(customerRegisterReq.getCustomerNickname());
        customer.setCustomerAddr(customerRegisterReq.getCustomerAddr());
        customer.setCustomerPhone(customerRegisterReq.getCustomerPhone());

        String password = customerRegisterReq.getCustomerPwd();
        String salt = saltUtil.genSalt();
        customer.setSalt(new Salt(salt));
        customer.setCustomerPwd(saltUtil.encodePassword(salt, password));
        customerRepository.save(customer);
    }

    //소비자 - 로그인
    @Override
    public Customer loginCustomer(String customerId, String customerPwd) throws Exception {
        Customer customer = customerRepository.findByCustomerId(customerId);
        if (customer == null) throw new Exception("아이디가 틀립니다.");
        String salt = customer.getSalt().getSalt();
        customerPwd = saltUtil.encodePassword(salt, customerPwd);
        if (!customer.getCustomerPwd().equals(customerPwd))
            throw new Exception("비밀번호가 틀립니다.");
        return customer;
    }

    //판매자 - 로그인
    @Override
    public Seller loginSeller(String sellerId, String sellerPwd) throws Exception {
        Seller seller = sellerRepository.findBySellerId(sellerId);
        if (seller == null) throw new Exception("아이디가 틀립니다.");
        String salt = seller.getSalt().getSalt();
        sellerPwd = saltUtil.encodePassword(salt, sellerPwd);
        if (!seller.getSellerPwd().equals(sellerPwd))
            throw new Exception("비밀번호가 틀립니다.");
        return seller;
    }

    //비밀번호 변경 - 판매자
    @Override
    public void changeSellerPassword(Seller seller, String sellerPwd, String sellerPwdUpdate) throws Exception{
        if(seller == null) throw new Exception("멤버가 조회되지 않습니다.");

        String salt_before = seller.getSalt().getSalt();
        sellerPwd = saltUtil.encodePassword(salt_before, sellerPwd);
        if (!seller.getSellerPwd().equals(sellerPwd))
            throw new Exception("비밀번호가 틀립니다.");

        String salt = saltUtil.genSalt();
        seller.setSalt(new Salt(salt));
        seller.setSellerPwd(saltUtil.encodePassword(salt, sellerPwdUpdate));
        sellerRepository.save(seller);
    }

    @Override
    public void changeCustomerPassword(Customer customer, String customerPwd, String customerPwdUpdate) throws Exception{
        if(customer == null) throw new Exception("멤버가 조회되지 않습니다.");

        String salt_before = customer.getSalt().getSalt();
        customerPwd = saltUtil.encodePassword(salt_before, customerPwd);
        if (!customer.getCustomerPwd().equals(customerPwd))
            throw new Exception("비밀번호가 틀립니다.");

        String salt = saltUtil.genSalt();
        customer.setSalt(new Salt(salt));
        customer.setCustomerPwd(saltUtil.encodePassword(salt, customerPwdUpdate));
        customerRepository.save(customer);
    }

    @Override
    public void logout(String accessToken, String refreshToken) {
        redisUtil.setBlackList(accessToken, "accessToken", jwtUtil.getExpiration(accessToken));
    }

}