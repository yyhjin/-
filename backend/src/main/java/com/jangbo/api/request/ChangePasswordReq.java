package com.jangbo.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordReq {
    String password;
    String passwordUpdate;
}