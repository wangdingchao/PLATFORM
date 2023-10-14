package com.example.platform.pojo;

import lombok.Data;

import java.util.Set;

@Data
public class ScanKeyResult {

    /**
     * 下标
     */
    private String cursor;

    private Set<String> keys;
}
