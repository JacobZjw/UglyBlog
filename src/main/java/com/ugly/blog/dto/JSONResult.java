package com.ugly.blog.dto;

import lombok.Data;

/**
 * @author JwZheng
 * @date 2021/4/6 20:16
 */
@Data
public class JSONResult {
    private Integer code = 200;
    private String msg = "success";
    private Object data = null;
}
