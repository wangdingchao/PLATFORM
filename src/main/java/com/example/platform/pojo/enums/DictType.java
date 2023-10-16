package com.example.platform.pojo.enums;


import com.example.platform.utils.ListUtil;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * 业务字典类型
 */
@Getter
public enum DictType implements BaseEnum {

    //AREA("区域"),
    COMPANY("组织机构所属公司"),
    DEALER("经销商"),
    BRAND_LABEL("品牌"),
    KHLY("客户来源", true, 1),
    REGION("接单范围", true, 1),//区域改为接单范围
    BRAND("品类", true),//李伟
    SALES("营销活动", true), //云寿
    DICT_TYPE("业务类型", true),
    COOPERATOR_TYPE("合作方类型", true),
    NEW_COOPERATOR_TYPE("新合作方类型", true),
    COOPERATOR_LEVEL("合作方等级",true),
    ORDER_TYPE("订单类型", true),
    JFHL("积分换礼", false, 1, true, "number"),
    //SOGAL_MATERIAL("索菲亚物料", false, 1, false, "",true),
    //MILANA_MATERIAL("米兰纳物料", false, 1, false, "",true),
    //SCHMIDT_MATERIAL("司米物料", false, 1, false, "",true),
    UNIT("单位", false, 1, false, "", true),
    CATEGORY("物料分类", false, 1, false, "", true),
    PRODUCT_CATEGORY("产品品类", false, 1, false, "", true),
    PRODUCT_STYLE("产品风格", false, 1, false, "", true),

    INEFFECTIVE_REASONS("无效原因"),
    DECORATION_STAGE("装修阶段"),
    FAMILY_COMPOSITION("家庭构成"),
    HOUSE_TYPE("房屋类型"),
    //START_DATE("开工时间"),
    FWHX("房屋户型"),
    //HOUSING_AREA("房屋面积"),
    DECORATION_STYLE("装修风格"),
    //DECORATION_BUDGET("装修预算"),
    PROFESSION("职业"),
    //INTENTION("意向品类"),
    SCHMIDT_SUB_TYPE("司米子单类型"),
    SOGAL_SUB_TYPE("索菲亚子单类型"),
    MILANA_SUB_TYPE("米兰纳子单类型"),
    //COMPANY_CONFIGURATION("公司配置"),//主营需要 孙鑫
    SERVICE_ORDER_ISSUE("工单问题类型"),
    SERVICE_COMPLAINTS_LINK("工单投诉环节"),
    DELIVERY_CHECK_ISSUE("物流验收问题类型"),
    TRADE_METHOD("支付方式"),
    FINANCE_SECONDWAY("支付方式"),
    BUSINESS_INFORMATION("商户信息"),
    //    CATEGORY("品类"),
    COUPON_EVENT("优惠券事件"),
    BANK("银行"),
    SOGAL_DELIVERY_AREA("索菲亚送货区域"),
    SCHMIDT_DELIVERY_AREA("司米送货区域"),
    SOGAL_PRODMARK("工厂产品编码"),
    BRAND_ORDER_CHANGE_EMP_REASON("品牌单负责人变更原因"),
    COMMUNICATION_TOOL("沟通工具"),//沟通工具与推广平台暂时只有电商来源的客户用到
    EXTENSION_PLATFORM("推广平台"),
    RETURN_REASON("退货原因"),//孙鑫
    WORKFLOW_TYPE("工作流类型"),//毛志涛
    SERVICE_ORDER_SERVICE_REASONS("售后服务维修原因"),//改补原因 汤荣谚
    SERVICE_ORDER_CLOSING_REASONS("工单关闭原因"),//工单关闭原因
    SERVICE_ORDER_MATERIAL_COLOR("改补单物料花色"),
    INSTALL_AREA("安装片区"),//冀鹏宇
    INSTALL_APP("安装APP"),
    INSTALL_SIGN_IN_APPEAL_REASON("安装签到申诉原因"),
    SG_ABILITY_SERVICE_ITEM_TYPE("中台力所能及服务项类型"),
    INSTALL_PUNISH_TYPE("扣罚分类"),
    DELIVERY_AREA("物流片区"),
    DELIVERY_ACCOUNT_COMPLAIN("物流运费申诉"),
    INSTALL_SPECIAL_ACCOUNT_REASONS("特殊工费申请原因"),
    GLASS("木门玻璃款式"), //孙鑫20181106
    DOOR_GLASS_STYLE("木门玻璃款式"),
    MOULDING_LINE("口线"),//孙鑫
    DOOR_MOULDING_LINE("木门口线款式"),
    PRODUCT_CONTRACT_DATE("合同产品类型"),
    INVOICE_CONTENT("发票内容"),
    COOPERATOR_METHOD("合作方式"),
    LINBAO_KFBM("拎包开发部门"),
    LINBAO_HZFS("拎包合作方式"),
    COUPON_ITEM("优惠券发放原因"),
    X_ROLE("X计划角色"),
    CUT_TYPE_VALUE("切角产品编码"),
    //FURNITURE("家具家品"),
    RECOMMENDATION_SHIP("推荐官身份"),
    ASSIST_SUPPLIER("外协供应商"),
    MESSAGE_TEMPLATE("短信模板"),

    MILANA_COLOR("米兰纳花色"),
    SCHMIDT_COLOR("司米花色"),
    SOGAL_COLOR("索菲亚花色", false, 1, false, "", true),//主营需要
    MATERIAL_COLOR("物料花色", false, 1, false, "", true),

    MATERIAL_TEXTURE("物料材质", false, 1, false, "", true),
    SOGAL_TEXTURE("索菲亚材质", false, 1, false, "", true),
    MILANA_TEXTURE("米兰纳材质", false, 1, false, "", true),
    SCHMIDT_TEXTURE("司米材质", false, 1, false, "", true),

    MEAL_TYPE("套餐类型"),

    JM_CATEGORY("集美明细类别"),
    MARKET("市场"),

    // start  使用场景:后台管理系统、CMS、C端
    CMS_DEALER_ENUMERATE("经销商", true, 1),
    ADMINISTRATIVE_REGION("行政区域"),
    PARTNERSHIP("创薪合伙人身份"),
    CXHHR_REJECT_REASON("创薪合伙人审核拒绝原因"),
    CMS_COLUMN("cms栏目", true, 1),
    XFS_SUPPORT_BANKS("薪福社支持银行"),
    EXPRESS_COMPANY("快递公司"),
    HHR_SOURCE("合伙人所属渠道", true),
    HHR_UNSETTLEMENT_REASONS("合伙人奖励不核算原因"),
    SG_IMITATE_SOLID_SERIES("产品标识"),
    // 使用场景:后台管理系统、CMS、C端      end

    // 使用场景:dsp评价模块

    REVIEW_SOURCE("回访来源"),
    SATISFACTION("满意度"),
    NOT_SATISFACTION_REASONS("不满意原因"),
    TRAFFIC_COST_REASONS("电商流量不收费原因"),
    AFTER_FOLLOW_UP_RECORDS("工单跟进记录"),

    // 使用场景:呼叫模块;
    CALL_PROBLEM_TYPE("呼叫问题类型"),
    CALL_PRODUCT_TYPE("呼叫产品分类"),

    //财务录入收款模块
    //RECEIVABLE_AMOUNT("收款品类"),
    PRO_TYPE("套餐物料类型"),
    //费用
    COST_TYPE("费用类型"),

    //发票类型
    INVOICE_TYPE("发票类型"),
    //环节满意度评价
    CUSTOMER_SATISFACTION_REASON("客户满意度原因"),

    //测量完成环节
    ERROR_CLOCK("异常打卡"),

    // 奖励指标(单位、业务环节)
    KPI_UNIT("指标单位"),
    BUSINESS_DIVISION("事业部"),

    CONTRACT_SIGN_COMPANY("签约公司类型"),

    EMP_HOBBY("爱好"),

    ORG_OUTSIDE("编外的部门"),
    ORG_CUSTOM_ATTRIBUTE("组织机构自定义属性值"),
    SALE_TYPE("销售类型"),

    //中台业务字典
    SG_MM_CHANGE_TYPE("木门改补类型"),
    SG_HH_CHANGETYPE("产生原因（针对木门订单的改补物料）"),
    SG_CAB_MACTH_TYPE("橱柜对色类型"),
    SG_OU_NAME("生产 OU"),

    LINE_METER_TYPE("延米类型"),
    SHOP_MAIN_BUSINESS("门店主业"),

    DEDUCTION_REASON("快捷扣款"),
    INDUCTION_REASON("快捷收款"),

    SPACE("DIY空间"),
    FS_ACCOUNT("分销账户"),

    IDS_REASONS_FOR_CONTRACT_REJECTION("合同拒绝理由"),
    IDS_SYS_USER_SEX("用户性别"),
    IDS_SYS_SHOW_HIDE("菜单状态"),
    IDS_SYS_NORMAL_DISABLE("系统开关"),
    IDS_SYS_JOB_STATUS("任务状态"),
    IDS_SYS_JOB_GROUP("任务分组"),
    IDS_SYS_YES_NO("系统是否"),
    IDS_SYS_NOTICE_TYPE("通知类型"),
    IDS_SYS_NOTICE_STATUS("通知状态"),
    IDS_SYS_OPER_TYPE("操作类型"),
    IDS_SYS_COMMON_STATUS("系统状态"),
    IDS_CRM_CHANNAL_STATUS("渠道状态"),
    IDS_CRM_CLIENT_LEVEL("客户等级"),
    IDS_CRM_NATURE("性质"),
    IDS_CRM_FURNISH_TYPE("装修类型"),
    IDS_CRM_IS_READY_HOUSE("期房/现房"),
    IDS_CRM_HOUSE_TYPE("房屋类型"),
    IDS_CRM_DECORATION_STYLE("装修风格"),
    IDS_CRM_AFTER_LOG_RECORD_STATE("后端日志记录状态"),
    IDS_CRM_COMMUNICATE_RESULTS("沟通结果"),
    IDS_CRM_CAUSE_OF_INVALIDITY("无效原因"),
    IDS_SYS_USER_TYPE("用户类型"),
    IDS_SRM_MATERIAL_PURCHASE_TYPE("物料数据-采购类型"),
    IDS_SRM_MATERIAL_SALES_SCENARIO("物料数据-销售场景"),
    IDS_SRM_MATERIAL_STATUS("物料数据-状态"),
    IDS_SRM_MATERIAL_UNIT("物料数据-单位"),
    IDS_SRM_BASE_MATERIAL_TYPE_STATUS("物料类型基础数据-状态"),
    IDS_SRM_BASE_MATERIAL_TYPE_CLASSIFY("物料类型基础数据-分类编码"),
    IDS_SRM_SUPPLIER_STATUS("供应商数据-状态"),
    IDS_SRM_SRM_SUPPLIER_SETTLE_ACC_DEFAULT("供应商结算账户数据-默认结算账户"),
    IDS_SRM_SRM_SUPPLIER_INSIDE("供应商数据-是否内部供应商"),
    IDS_SRM_CRM_STAGE_TYPE("线索阶段"),
    IDS_SRM_BANK_CODE("银行字典"),
    IDS_CRM_PICTURE_TYPE("CRM图片"),
    IDS_CRM_PICTURE_POSITION("CRM图片位置"),
    IDS_CRM_SEND_PERSONNEL_CLASSIFY("待派分类"),
    IDS_CRM_THREAD_DISPLAY_STATE("线索下拉展示状态"),
    IDS_CRM_INVALID_TYPE("无效类型"),
    IDS_CRM_DESIGNER_DISPLAY_LOGO("设计师展示标识"),
    IDS_CRM_THREAD_DISPLAY_LOGO("线索下拉展示标识"),
    IDS_CRM_ORDER_DISPLAY_LOGO("订单下拉展示标识"),
    IDS_CRM_DESIGNER_DISPLAY_STATE("设计师下拉展示状态"),
    IDS_CRM_ORDER_TAB_DISPLAY_STATE("订单Tab展示状态"),
    IDS_CRM_PROJECT_TAB_DISPLAY_STATE("工程Tab展示状态"),
    IDS_CRM_CATEGORY_TYPE("品类"),
    IDS_CRM_CATEGORYDETAILS_TYPE("品类明细"),
    IDS_PROC_SPACE_TYPE("空间类型"),
    IDS_PROC_FOREMAN_SETTLEMENT("是否工长结算"),
    IDS_PROC_PROCESS_TYPE("工艺类型"),
    IDS_PROC_TYPE_WORK_TYPE("工种类型"),
    IDS_PROC_POINT_TYPE("点位类型"),
    IDS_PROC_DISCOUNT_METHOD("折价方式"),
    IDS_PROC_PRODUCT_PACKAGE_TYPE("产品包类型"),
    IDS_METHOD_OF_SALES("套餐销售方式"),
    IDS_MC_ACTIVITY_STATUS("活动状态(营销中心)"),
    IDS_MC_ACTIVITY_SUPERPOSITION("活动是否允许优惠叠加(营销中心)"),
    IDS_MC_ACTIVITY_TIME_PE("活动有效期是否永久有效(营销中心)"),
    IDS_MC_COUPON_APPLICABLE_SCENE("优惠券适用场景(营销中心)"),
    IDS_MC_COUPON_APPLICABLE_SCENE_SCOPE("优惠券适用商品范围(营销中心)"),
    IDS_MC_COUPON_APPLICABLE_SCENE_STAGE("优惠券适用阶段(营销中心)"),
    IDS_MC_COUPON_EFFECTIVE_TYPE("优惠券有效期类型(营销中心)"),
    IDS_MC_COUPON_GEN_MODE("优惠券生成方式(营销中心)"),
    IDS_MC_COUPON_LIMITED_CONDITION_UNIT("优惠券限领条件单位(营销中心)"),
    IDS_MC_ACTIVITY_PLATFORM("活动平台(营销中心)"),
    IDS_MATERIAL_POSITION("主材位置"),
    IDS_SC_BASE_COLLECTION_ACCOUNT_STATUS("收款账号状态(结算中心)"),
    IDS_SC_BASE_ACCOUNT_SYSTEM_BUSINESS_STATUS("账户体系状态(结算中心)"),
    IDS_SC_BASE_ACCOUNT_SYSTEM_MASTER("是否为主账户(结算中心)"),
    IDS_SC_BASE_ACCOUNT_SYSTEM_STATUS("账户交易类型(结算中心)"),
    IDS_SC_BASE_PAYMENT_CHANNEL_STATUS("支付渠道状态(结算中心)"),
    IDS_SC_BASE_POS_RECORD_STATUS("POS机器领用记录状态(结算中心)"),
    IDS_SC_BASE_POS_STATUS("POS机器状态(结算中心)"),
    IDS_SC_BASE_STORE_STATUS("店铺状态(结算中心)"),
    IDS_SC_BASE_STORE_LEVEL("店铺级别(结算中心)"),
    IDS_ENGM_REASSIGNMENT_TYPE("派/改"),
    IDS_SIGN_CONTRACT_STATUS("合同状态(电子合同)"),
    IDS_SIGN_CONTRACT_TYPE("合同类型(电子合同)"),
    IDS_ENGM_DELAY_REWARD_STATUS("延期奖惩状态"),
    IDS_SC_PROJECT_SETTLEMENT_ATTRIBUTE_TYPE("工程结算属性类型(结算中心)"),
    IDS_SC_PROJECT_SETTLEMENT_ATTRIBUTE_STATUS("工程结算属性状态(结算中心)"),
    IDS_SC_PROJECT_SETTLEMENT_ATTRIBUTE_ALLOW_MODIFY("工程结算属性是否允许修改(结算中心)"),
    IDS_SC_PROJECT_SETTLEMENT_EXPRESSION_MODE("工程结算表达式模式(结算中心)"),
    IDS_SC_PROJECT_PAYMENT_BATCH_STATUS("工程结算付款批次状态(结算中心)"),
    IDS_SC_PROJECT_PAYMENT_BILL_STATUS("工程结算付款单状态(结算中心)"),
    IDS_SC_PROJECT_SETTLEMENT_BILL_STATUS("工程结算对账单状态(结算中心)"),
    IDS_SYS_REMIND_TYPE_STATUS("提醒类型状态"),
    IDS_CHANGE_ORDER_AUDIT_STATE("变更单审核状态"),
    IDS_SYS_CLIENT_TYPE("客户端类型"),
    IDS_SRM_DELIVERY_STATUS("发货单状态(物料模块)"),
    IDS_SRM_DELIVERY_LIST_STATUS("发货单明细状态(物料模块)"),
    IDS_SRM_PRE_SETTLEMENT_BATCH_STATUS("预结算批次状态(物料模块)"),
    IDS_ORDER_PURCHASE_ORDER_STATUS("采购单状态"),
    IDS_ORDER_PURCHASE_STATE("供应商状态"),
    IDS_ORDER_APPLICATION_STATUS("采购单物料状态(材料中心)"),
    IDS_ORDER_PRO_DISCLOSURE_STATE("预约交底状态"),
    IDS_SC_SUPPLIER_PAYMENT_BATCH_STATUS("付款批次状态(结算中心)"),
    IDS_SC_SUPPLIER_RECONCILIATION_BATCH_STATUS("对账批次状态(结算中心)"),
    IDS_SC_SUPPLIER_SETTLEMENT_BATCH_STATUS("结算批次状态(结算中心)"),
    IDS_SC_TRAN_RECORD_STATUS("交易记录状态(结算中心)"),
    IDS_ORDER_PROJECT_CHECKED("工程部审核"),
    IDS_ORDER_FINANCE_CHECKED("财务部"),
    IDS_SC_TRAN_PAYMENT_TYPE("付款类型(结算中心)"),
    IDS_SC_BASE_PAYMENT_CHANNEL_TYPE("支付渠道类型(结算中心)"),
    IDS_SC_REFUND_ORDER_STATUS("退款单状态(结算中心)"),
    IDS_SC_REFUND_ORDER_REASON("退单原因(结算中心)"),
    IDS_SC_PROJECT_SETTLEMENT_BILL_NEW_STATUS("工程结算对账单状态(结算中心)(新)"),
    IDS_SC_REFUND_ORDER_NEW_STATUS("退款单状态(结算中心)"),
    IDS_SC_REFUND_ORDER_TYPE_NEW("退款方式"),
    IDS_SC_REFUND_TRANSACTION_RECORD_STATUS("退款交易记录状态(结算中心)"),
    IDS_SRM_MATERIAL_GROUP("物料分组(SRM)"),
    IDS_ENGM_PERSON_COMPANY("工长所属公司"),
    IDS_SC_SETTLEMENT_MODULE_NAME("结算模块名"),
    IDS_SRM_MATERIAL_SETTLEMENT_MODE("物料结算模式(SRM)"),
    IDS_SRM_MATERIAL_PRICE_ADJUSTMENT_ORDER_STATUS("价格调整单状态(SRM)"),
    IDS_SRM_MATERIAL_PRICE_STATUS("物料价格状态(SRM)"),
    IDS_ORDER_DELAY_HANDLE_STATE("延期单状态"),
    IDS_INVOICE_TYPE("发票类型"),
    IDS_INVOICE_CONTENT("发票内容"),
    IDS_INVOICE_METHOD("开票方式"),
    IDS_INVOICE_STATUS("发票状态"),
    IDS_INVOICE_TAITOU("发票抬头"),
    IDS_SYS_CLIENT_SCOPE("客户端作用域 (system系统模块)"),
    IDS_SYS_CLIENT_GRANT_TYPE("客户端授权方式 (system系统模块)"),
    IDS_MC_COUPON_TYPE("优惠券类型"),

    BIG_SCREEN("大屏"),
    MINI_APP("小程序");

    private String message;

    /**
     * 是否需要控制业务权限
     */
    private boolean privilege = false;

    /**
     * 查询层级
     */
    private int level = 1;

    /**
     * 别名alias是否必须
     */
    private boolean aliasRequired;

    /**
     * 指定别名数据类型（如有需要）
     */
    private String aliasDataType;

    /**
     * 同一业务类型(type)下,显示值（又叫中文值、LABEL）是否唯一：true-唯一，不允许重复，false-可以重复
     */
    private boolean uniqueLabel;


    DictType(String message) {
        this.message = message;
    }

    DictType(String message, boolean privilege) {
        this(message);
        this.privilege = privilege;
    }

    DictType(String message, boolean privilege, int level) {
        this(message);
        this.privilege = privilege;
        this.level = level;
    }

    DictType(String message, boolean privilege, int level, boolean aliasRequired) {
        this(message);
        this.privilege = privilege;
        this.level = level;
        this.aliasRequired = aliasRequired;
    }

    DictType(String message, boolean privilege, int level, boolean aliasRequired, String aliasDataType) {
        this(message);
        this.privilege = privilege;
        this.level = level;
        this.aliasRequired = aliasRequired;
        this.aliasDataType = aliasDataType;
    }

    DictType(String message, boolean privilege, int level, boolean aliasRequired, String aliasDataType, boolean uniqueLabel) {
        this(message);
        this.privilege = privilege;
        this.level = level;
        this.aliasRequired = aliasRequired;
        this.aliasDataType = aliasDataType;
        this.uniqueLabel = uniqueLabel;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    public boolean isPrivilege() {
        return this.privilege;
    }

    public boolean isAliasRequired() {
        return this.aliasRequired;
    }

    public boolean isUniqueLabel() {
        return this.uniqueLabel;
    }

    public static List<DictType> getPrivilegeType() {
        return ListUtil.filter(Arrays.asList(DictType.values()), (i) -> i.isPrivilege());
    }

    public static List<DictType> getAliasRequired() {
        return ListUtil.filter(Arrays.asList(DictType.values()), (i) -> i.isAliasRequired());
    }

    public static List<DictType> getUniqueLabel() {
        return ListUtil.filter(Arrays.asList(DictType.values()), (i) -> i.isUniqueLabel());
    }

    public static DictType findDictType(String code) {
        return ListUtil.find(Arrays.asList(DictType.values()), (i) -> i.getCode().equals(code));
    }

    public static List<DictType> getAll() {
        return Arrays.asList(DictType.values());
    }
}