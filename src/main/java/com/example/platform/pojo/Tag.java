package com.example.platform.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 标签
 * 通过TagBinding实现打标签
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tag {

    @NotNull
    @Length(max = 36)
    private String id;

    //相关领域（标签分组）
    @NotNull
    private Realm realm;

    //标签名
    @NotNull
    @Length(max = 20)
    private String name;

    //标签别名
    @NotNull
    @Length(max = 50)
    private String alias;

    //标签内容
    @Length(max = 100)
    private String content;

    @NotNull
    private boolean interior;

    private String creatorId;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date timeBinding;

    private String rgb;

    @JsonProperty
    public boolean isInterior(){
        return interior;
    }
}