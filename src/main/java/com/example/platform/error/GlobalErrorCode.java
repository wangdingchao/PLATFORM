package com.example.platform.error;

/**
 * @author wdc
 * @date 2023/10/10
 * @description
 */
public enum GlobalErrorCode implements BaseErrorCode{


        UNKNOWN("999", "未知错误"),
        DATA_SAVE_ERROR("800", "数据保存错误"),
        DATA_UPDATE_ERROR("801", "数据修改错误"),
        DATA_DELETE_ERROR("802", "数据删除错误"),
        DATA_QUERY_ERROR("803", "数据查询错误"),
        DATE_KEY_ERROR("804", "相关数据重复，请查验是否重复操作"),
        MASTER_SLAVE_NOT_SYNC("805", "主从不同步"),
        USER_NOT_EXIST("810", "用户登录已过期，请重新登录"),
        USER_NOT_LOGIN("811", "用户未登录"),
        USER_NOT_PRIVILEGE("812", "用户权限不足"),
        CAPTCHA_ERROR("813", "验证码或服务码错误"),
        CUSTOMER_NO_VALIDATE("814", "客户未实名认证"),
        DATA_EXPIRE("815", "数据失效，请刷新页面"),
        DATA_DECRYPT("816", "数据解密失败"),
        RPC_FAILED("830", "服务调用失败"),
        RPC_TIMEOUT("831", "服务调用超时"),
        MANY_REQUEST("832", "请不要访问太频繁"),
        PARAM_LENGTH_ERROR("850", "参数长度太长"),
        PARAM_NULL_ERROR("851", "非空参数不能为空"),
        PARAM_ERROR("852", "参数异常"),
        PARAM_VALID_ERROR("853", "参数不合法"),
        NAME_IS_EXIST("854", "名称重复"),
        FILE_TEMPLATE_ERROR("855", "模板不合法"),
        NOT_YN("856", "是否类型请填写Y或者N，不可以不填写"),
        X_SOCKET_TIMEOUT("857", "X计划连接超时"),
        DATA_NO_UPDATE("858", "该数据不允许修改"),
        DATA_NO_DELETE("859", "该数据不允许删除"),
        DATA_NO_FIND("860", "该数据不存在"),
        MIDDLE("861", "广州中台服务返回信息"),
        SIGN_FAIL("862", "验证签名失败"),
        CONVERT_FAIL("863", "转化异常"),
        OA("864", "请求OA失败"),
        SERVER_CERTIFICATE_ERROR("865", "证书文件有问题，请核实"),
        SERVER_CERTIFICATE_PATH_NULL("866", "请确保证书文件地址已配置，请核实"),
        SERVER_CERTIFICATE_NOT_FOUND("867", "证书文件找不到"),
        SERVER_CERTIFICATE_PATH_ERROR("868", "读取证书文件有问题"),
        NOT_CSV("869", "文件扩展名不对，需要CSV格式文件"),
        WITHOUT_DATA("870", "导入信息不能为空"),
        EMPTY("871", "存在空白行，请去掉"),
        HTTP_METHOD("872", "Http请求方式未定义"),
        WOA("873", "客户未在%s公众号完成手机号的绑定，请引导客户完成绑定。"),
        DEALER_CODE("874", "操作人尚未分配对应区域业务权限"),
        DALER_ORG("875", "组织机构与经销商尚未配置"),
        PROD_ERROR("876", "物料迁移失败"),
        CATEGORY_BRAND_NULL("877", "品类尚未设置品牌关系"),
        CATEGORY_BRAND_VALIDATE("878", "%s品类需要指定所属品牌"),
        WECOM_MESSAGE_SEND("880", "发送企微消息失败"),
        MIDDLE_SERVICE_ERROR("881", "中台服务异常"),
        IDS_SIGN_ERROR("882", "构建IDS签名失败"),
        COOPERATOR_CONTRACT("883", "请保证合作方合同已签署并审批通过后，重新尝试！"),
        IDS_SERVICE_ERROR("884", "ids服务异常");

        private String code;
        private String message;

        private GlobalErrorCode(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public String getMessage() {
            return this.message;
        }

}
