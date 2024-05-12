package org.example.backend.DTO.response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResponseMessage {
    private long statusCode;
    private String message;
    private Object data;

    public ResponseMessage() {
    }

    public ResponseMessage(long statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}

