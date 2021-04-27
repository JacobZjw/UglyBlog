package com.ugly.blog.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author JwZheng
 * @date 2021/4/27 13:57
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Notice implements Serializable {

    private static final long serialVersionUID = -452830285567833929L;

    /**
     * ID
     */
    private Integer noticeId;

    /**
     * 作者ID
     */
    private Integer authorId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否删除 1是 0否
     */
    private Integer isDel;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建者信息
     */
    private String authorNickname;
}
