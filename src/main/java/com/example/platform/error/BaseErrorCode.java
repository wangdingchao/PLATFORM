package com.example.platform.error;

import com.example.platform.dto.JsonResult;
import com.example.platform.pojo.enums.BaseEnum;

/**
 * @author wdc
 * @date 2023/10/10
 * @description
 */
public interface BaseErrorCode extends BaseEnum {
    default JsonResult errorResult() {
        return JsonResult.fail(this.getCode(), this.getMessage());
    }
}
