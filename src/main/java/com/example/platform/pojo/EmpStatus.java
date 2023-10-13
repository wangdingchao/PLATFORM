package com.example.platform.pojo;

import com.example.platform.pojo.enums.BaseEnum;
import com.example.platform.utils.ListUtil;
import com.google.common.collect.ImmutableSet;

import lombok.Getter;

import java.util.*;

@Getter
public enum EmpStatus implements BaseEnum {

    WAIT_ONBOARDING("待录用"),
    TRAIL("试用", true),
    TRAINEE("实习", true),
    WAIT("待岗", true),
    FORMAL("正式", true),
    STOP("停薪留职", true),
    RETIRED("退休"),
    DIMISSION("离职");

    private String message;

    //判断员工是否可用
    private Boolean isUse = false;

    private EmpStatus(String message) {
        this.message = message;
    }

    private EmpStatus(String message, boolean use) {
        this.message = message;
        this.isUse = use;
    }

    public String getCode() {
        return this.name();
    }

    public Boolean isUse(){
        return this.isUse;
    }

    public static List<EmpStatus> getUsing(){
        return ListUtil.filter(Arrays.asList(EmpStatus.values()), i -> i.isUse());
    }

    public static EmpStatus getEmpStatus(String code) {
        EmpStatus empStatus = null;
        for (EmpStatus status : EmpStatus.values()) {
            if (status.getCode().equals(code)) {
                empStatus = status;
            }
        }
        return empStatus;
    }


    /**
     * 定义状态流转规则
     * Map<A, BSet>  允许BSet的所有状态到A
     */
    private static Map<String, Set<String>> STATUS_TRANSFER_MAP = new HashMap(){
        {
            put(TRAINEE, ImmutableSet.of(
                    WAIT_ONBOARDING
            ));
            put(TRAIL, ImmutableSet.of(
                    WAIT_ONBOARDING
            ));
            put(FORMAL, ImmutableSet.of(
                    TRAINEE,
                    TRAIL,
                    WAIT
            ));
            put(WAIT, ImmutableSet.of(
                    FORMAL //只有正式员工才能到待岗状态
            ));

            put(DIMISSION, ImmutableSet.of(
                    TRAINEE,
                    TRAIL,
                    FORMAL,
                    WAIT
            ));

            put(RETIRED, ImmutableSet.of(
                    FORMAL
            ));

        }
    };


    /**
     * 状态流转规则-状态变更check
     * @param fromStatus
     * @param toStatus
     * @return
     */
    public static boolean updateStatusCheck(EmpStatus fromStatus, EmpStatus toStatus) {
        Set<String> statusSet = STATUS_TRANSFER_MAP.get(toStatus);
        if(statusSet == null) {
            return false;
        }
        return statusSet.contains(fromStatus);
    }

}
