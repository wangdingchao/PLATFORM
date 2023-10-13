package com.example.platform.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiddleDealerEmployee {

    private String id;

    private String xId;

    private String name;

    private String phone;

    private String empId;

    private String gender;

    private String dealerCode;

    private String loginUser;

    private String loginPassword;

    private boolean primary;

    private String shopNum;

    private String orgId;

}
