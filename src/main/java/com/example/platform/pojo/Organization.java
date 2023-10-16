package com.example.platform.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 经销商组织机构
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Organization implements Serializable {

    private String dealerCode;

    private String id;

    //全称
    @NotNull
    private String name;

    //简称
    private String shortName;

    //组织机构唯一编码
    @NotNull
    @Length(max = 10)
    private String code;

    //编码路径
    private String codePath;

    private String oaCodePath;

    //索菲亚的编码
    private String sogalCode;

    //司米的编码
    private String schmidtCode;

    //米兰纳的编码
    private String milanaCode;

    //华鹤的编码
    private String hhCode;

    //装企下单编码
    private String zqCode;

    //司米装企下单编码
    private String schmidtZQCode;

    //上级机构id
    private String parentId;

    private String oaParentId;

    //状态
    private Boolean valid;

    /**
     * 创建时间
     */
    private Date timeCreated;

    //无效（关闭）时间，机构改为有效状态时应清空
    private Date invalidTime;

    //地址（市）
    @Length(max = 100)
    private String city;

    //地址（区）
    @Length(max = 100)
    private String area;

    //地址（街道、园区、写字楼）
    @Length(max = 100)
    private String addr;

    //门牌号
    private String number;

    //经度
    private Double longitude;

    //纬度
    private Double latitude;

    //索菲亚面积
    private Double sogalAcreage;

    //司米面积
    private Double schmidtAcreage;

    //米兰纳面积
    private Double milanaAcreage;

    //华鹤分摊面积
    private Double hhAcreage;

    //装企分摊面积
    private Double zqAcreage;

    //司米装企分摊面积
    private Double schmidtZQAcreage;

    //是否有结对存在
    private Boolean troop;
    //结对数量
    private Integer troopNum;
    //纵队数量
    private String columnNum;
    //纵队类型
    private String teamTypes;

//    //主营品牌
    private String mainBrand;

    //X计划门店ID
    private String orgId;

    //来源
    private String source;

    //是否有大本营存在
    private Boolean baseTeam;
    
    private String storeLevel;

    private boolean dingSign;

    @Transient
    private List<Organization> children;            //子集组织机构集合

    @Transient
    private List<Tag> tags;                         //组织机构具有的标签


    private String companyCode;
    private String brandLabel;
    //签约公司
    private String signCompany;
    private String signCompanyId;
    private String signCompanyAccount;

    private String sogalRecompany;
    private String sogalRecompanyId;
    private String sogalRecompanyAccount;
    private String schmidtRecompany;
    private String schmidtRecompanyId;
    private String schmidtRecompanyAccount;
    private String milanaRecompany;
    private String milanaRecompanyId;
    private String milanaRecompanyAccount;
    /**
     * 地板出款公司
     */
    private String floorRecompany;
    /**
     * 地板出款公司id
     */
    private String floorRecompanyId;
    /**
     * 地板出款账户
     */
    private String floorRecompanyAccount;


    private String mainBrandLabel;

    private String sogalAccountNum;
    private String schmidtAccountNum;
    private String milanaAccountNum;
    private String hhAccountNum;
    private String zqAccountNum;
    private String schmidtZQAccountNum;


    /**
     * 主业
     */
    private String businessType;
    /**
     * 上级组织机构名称
     */
    @Transient
    private String parentName;

    /**
     * 之前的企微同步状态
     */
    private Boolean isPreDingSign;

    private String orgType;

}
