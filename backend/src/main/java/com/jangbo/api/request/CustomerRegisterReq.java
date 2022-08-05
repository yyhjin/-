package com.jangbo.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jangbo.db.entity.InterStore;
import com.jangbo.db.entity.Orders;
import com.jangbo.db.entity.Salt;
import com.jangbo.db.entity.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CustomerRegisterReq{

    private String customerId;
    private String customerName;
    private String customerNickname;
    private String customerAddr;
    private String customerPhone;
    private String customerPwd;

}
