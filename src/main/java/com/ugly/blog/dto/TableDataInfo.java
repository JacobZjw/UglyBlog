package com.ugly.blog.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 表格数据分页模型
 *
 * @author JwZheng
 * @date 2021/4/9 22:47
 */
@Data
public class TableDataInfo implements Serializable {

    private static final long serialVersionUID = 3150441027530205120L;

    /**
     * 状态码，成功：0，失败：1
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 总记录数
     */
    private Integer count;

    /**
     * 数据列表
     */
    private List<?> data;

}
