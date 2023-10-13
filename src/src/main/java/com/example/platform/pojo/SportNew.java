package com.example.platform.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @projectName: platform
 * @package: com.example.platform.pojo
 * @className: New
 * @author Shuai Fang
 * @description: 新闻类
 * @date: 2023/10/12 15:57
 * @version: 1.0
 */

@Data
public class SportNew {

    @TableId
    // 新闻id
    // news id, primary key.
    private Integer snId;

    // 新闻标题
    // News title
    private String sportNewTitle;

    // 新闻内容
    // News content
    private String sportNewContent;



}
