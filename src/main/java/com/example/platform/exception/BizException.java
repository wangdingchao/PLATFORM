package com.example.platform.exception;

import com.example.platform.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 业务异常
 */
@Data
@AllArgsConstructor
public class BizException extends Exception {

    private String code;
    private String message;
    private String m;

    public BizException(BaseErrorCode error, String... errorMsg) {
        this.code = error.getCode();
        StringBuilder msg = new StringBuilder();
        for (String m : errorMsg) {
            msg.append(",");
            msg.append(m);
        }
        this.message = error.getMessage() + msg;
    }

    public BizException(BaseErrorCode error, boolean flag, String... errorMsg) {
        this.code = error.getCode();
        String msg = String.format(error.getMessage(), errorMsg);
        this.message = msg;
    }

    public BizException(BaseErrorCode error, String message, String m) {
        this.code = error.getCode();
        this.message = message;
        this.m = m;
    }

    public BizException(String error, String message) {
        this.code = error;
        this.message = message;
    }
}
