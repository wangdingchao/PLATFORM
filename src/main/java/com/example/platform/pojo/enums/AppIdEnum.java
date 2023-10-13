package com.example.platform.pojo.enums;

/**
 * @author hyl
 */

public enum AppIdEnum {

    // 主营
    DSP_DEV_WEB("DSP-DEV-WEB","6a9c233f-d2c1-4bff-b93d-093663b23c0a"),
    DSP_TEST_WEB("DSP-TEST-WEB","73691389-4b4e-4bd3-bfaa-cd3922b9d0b1"),
    DSP_TESTB_WEB("DSP-TESTB-WEB","a1cfed4c-9fdc-4946-addd-a5e44f33ff27"),
    DSP_TESTC_WEB("DSP-TESTC-WEB","80d05c65-b421-41ff-bd0e-93992ce3723b"),
    DSP_PRE_WEB("DSP-PRE-WEB","fb6027ed-7b63-4955-861b-cc486fe32bf1"),
    DSP_PROD_WEB("DSP-PROD-WEB","7d6dce54-0d24-432d-ad4c-4e3a8b3badd2"),

    // 索小秘
    CRM_DEV_MINAPP("CRM-DEV-MINAPP","a883e631-7a11-4180-9e35-d3b04ab422a4"),
    CRM_TEST_MINAPP("CRM-TEST-MINAPP","c030483f-7963-4a94-8e2b-8e3daf270e29"),
    CRM_TESTB_MINAPP("CRM-TESTB-MINAPP","dcad0a2b-4018-4b1a-b312-aa8c6906aa1d"),
    CRM_TESTC_MINAPP("CRM-TESTC-MINAPP","c7762ea0-dffb-43bb-a8c3-f4cab643bd60"),
    CRM_PRE_MINAPP("CRM-PRE-MINAPP","ff851fd1-cff5-410e-9b97-36ac316d49e9"),
    CRM_PROD_MINAPP("CRM-PROD-MINAPP","ace605ba-c02c-4aea-8336-8d432ec915ec"),

    // 安装
    INSTALL_DEV_MINAPP("INSTALL-DEV-MINAPP","874f7436-f925-4598-b3a0-d131e8d11d9c"),
    INSTALL_TEST_MINAPP("INSTALL-TEST-MINAPP","80d06e93-2a32-4b51-835f-5d83639f6e30"),
    INSTALL_TESTB_MINAPP("INSTALL-TESTB-MINAPP","fc339bc1-28dd-4f61-92e9-0b9b1850a0e8"),
    INSTALL_TESTC_MINAPP("INSTALL-TESTC-MINAPP","3f80f77c-66d5-44b7-adca-bea5532128c3"),
    INSTALL_PRE_MINAPP("INSTALL-PRE-MINAPP","5a9f37bc-cf39-447f-a9a8-724ade96bf11"),
    INSTALL_PROD_MINAPP("INSTALL-PROD-MINAPP","d1717d98-702f-401e-ad87-bd023b08bb7f"),

    // 物流
    DELIVERY_DEV_MINAPP("DELIVERY-DEV-MINAPP","4340c60b-1301-44f9-9824-ce24f813ecb3"),
    DELIVERY_TEST_MINAPP("DELIVERY-TEST-MINAPP","587f5890-898a-4a66-ad08-c26c91712cab"),
    DELIVERY_TESTC_MINAPP("DELIVERY-TESTC-MINAPP","60b03955-d8f9-49e4-b245-90c1448126e8"),
    DELIVERY_PROD_MINAPP("DELIVERY-PROD-MINAPP","7351c7e4-cfe2-4d13-a68e-f1b54851081f"),

    // OA系统
    MIS_DEV_WEB("MIS-DEV-WEB","4a1c830a-5364-425a-8717-015b3e5ff0b1"),
    MIS_TEST_WEB("MIS-TEST-WEB","b054b09a-1646-4fd1-b210-88dd8148b04b"),
    MIS_PROD_WEB("MIS-PROD-WEB","1d0b3c53-1ebf-4f82-b453-ada772aa7caa"),

    //
//    MARKET_DEV_H5("MARKET-DEV-H5","fd4ce707-a16c-4716-abd0-8e2335223e9b"),
//    MARKET_TEST_H5("MARKET-TEST-H5","6e69287f-71b2-4d29-9961-4b071796a6af"),
//    MARKET_TESTB_H5("MARKET-TESTB-H5","2c77dc4c-3907-45d8-b329-bdba13c0b7e6"),
//    MARKET_TESTC_H5("MARKET-TESTC-H5","3224ed32-c212-4fec-bd8d-e15b1150eebf"),
//    MARKET_PROD_H5("MARKET-PROD-H5","00fbb5e5-eccf-4f7a-96c4-c4c2ba0e7fb2"),

    // IDS供应商
    SCM_DEV_WEB("SCM-DEV-WEB","6bead363-dc02-4288-b599-d33947e6cb8c"),
    SCM_TEST_WEB("SCM-TEST-WEB","52f95f2f-f447-41c3-b251-9ea23667654c"),
    SCM_TESTB_WEB("SCM-TESTB-WEB","a400c1d3-56f5-4a6c-84fb-7edfa007b5a5"),
    SCM_PRE_WEB("SCM-PRE-WEB","2ad8c130-e9f4-4f9e-a8b9-fb3e5b01ddda"),
    SCM_PROD_WEB("SCM-PROD-WEB","bb7171d2-4ac6-42c5-a21b-0dbc7c0930ac"),

    // IDS工程端
    PROJ_DEV_MINAPP("PROJ-DEV-MINAPP","588e8aef-ed64-469b-b3b5-23d7cfcfb384"),
    PROJ_TEST_MINAPP("PROJ-TEST-MINAPP","4404d4d0-e05c-4bf6-b0a5-27f871d4efa9"),
    PROJ_TESTB_MINAPP("PROJ-TESTB-MINAPP","c589a41b-01e5-4714-8e12-c15f9df759f7"),
    PROJ_PRE_MINAPP("PROJ-PRE-MINAPP","62350578-6ef6-6c0c-6bd8-3ae827e82426"),
    PROJ_PROD_MINAPP("PROJ-PROD-MINAPP","52350578-5ef6-4c0c-8bd8-2ae827e8242a"),

    // 企微
    WECOM_DEV_QW("WECOM-DEV-QW","9ecffa85-23ed-46b4-847a-de01cacc1747"),
    WECOM_TEST_QW("WECOM-TEST-QW","a90c57e1-baba-4d12-8311-326397cbd542"),
    WECOM_TESTB_QW("WECOM-TESTB-QW","84cf8ae4-5716-4651-bd5c-33c60bf2090d"),
    WECOM_TESTC_QW("WECOM-TESTC-QW","7598031b-74fc-4a10-9bd9-f47e50b4037e"),
    WECOM_PRE_QW("WECOM-PRE-QW","dcf7a5db-b3ce-4ef5-ae48-3a7717e6f1cc"),
    WECOM_PROD_QW("WECOM-PROD-QW","31fc31dc-88aa-4bf0-8a29-ff12c7d732d0"),


    // 合作方带单
    COOPERATION_DEV_GZH("COOPERATION-DEV-GZH","112d7ad7-37da-4092-990f-d2c0553c2152"),
    COOPERATION_TEST_GZH("COOPERATION-TEST-GZH","fdb98552-b445-47d2-aff3-fccfad28ae0e"),
    COOPERATION_TESTB_GZH("COOPERATION-TESTB-GZH","0ffa4d2b-64d6-4b6e-90d2-ea6c5b77228e"),
    COOPERATION_TESTC_GZH("COOPERATION-TESTC-GZH","fce0e064-4039-472b-83b3-6542116e07be"),
    COOPERATION_PRE_GZH("COOPERATION-PRE-GZH","380794ba-263a-4ec2-9f5b-b777cefaf1e3"),
    COOPERATION_PROD_GZH("COOPERATION-PROD-GZH","7ee8efdc-4fcf-4775-8ac7-472849b4b264"),

    // 厨房护理服务商
    CFHL_DEV_GZH("CFHL-DEV-GZH","5cbdae53-6e64-4276-b52f-e876f9fbea6a"),
    CFHL_TEST_GZH("CFHL-TEST-GZH","2c66c8be-dff4-4726-ba3b-cb546f7f3dd9"),
    CFHL_TESTB_GZH("CFHL-TESTB-GZH","d66722bf-a0f2-492d-ac75-e37c973a718a"),
    CFHL_TESTC_GZH("CFHL-TESTC-GZH","b25b38b7-ae68-4e2a-9ac3-3cbe8f8d41a2"),
    CFHL_PRE_GZH("CFHL-PRE-GZH","2f930e48-989e-47db-be0a-4ae8cccf442f"),
    CFHL_PROD_GZH("CFHL-PROD-GZH","f1c2a83e-573e-4b2b-a7d0-5e7c87383175"),

    ;

    private final String key;

    private final String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    AppIdEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValueByKey(String key) {
        AppIdEnum[] values = AppIdEnum.values();
        for (AppIdEnum value : values) {
            if (key.equals(value.getKey())) {
                return value.getValue();
            }
        }
        return null;
    }


}
