package com.ugly.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author JwZheng
 * @date 2021/4/2 16:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag implements Serializable {

    private static final long serialVersionUID = -347237864974L;

    private Integer tagId;

    private String tagName;
}
