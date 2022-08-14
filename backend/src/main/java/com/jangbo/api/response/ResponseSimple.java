package com.jangbo.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseSimple {
    private String response;
    private String message;
    private Object data;

    public ResponseSimple(String response, String message, Object data) {
        this.response = response;
        this.message = message;
        this.data = data;
    }

}