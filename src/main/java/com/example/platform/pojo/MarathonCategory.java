package com.example.platform.pojo;

//import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 *  @Description: marathon category
 *  @Description: full marathon, half marathon, quarter marathon
 */

@Data
public class MarathonCategory {
//    @TableId
    private Integer id; // Marathon category id;
    private String categoryName; // Marathon category name;

}
