package com.example.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询结果
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedList<T> implements Serializable {

    private static final long serialVersionUID = 20171221L;

    //一页数据
    private List<T> results;

    //数据总量
    private Integer totalSize;

    //是否有下一页
    private Boolean hastNext;

    public PagedList(List<T> results, Integer totalSize) {
        this.results = results;
        this.totalSize = totalSize;
    }

    public PagedList(List<T> results, Boolean hastNext) {
        this.results = results;
        this.hastNext = hastNext;
    }
}
