package com.ugly.blog.controller.common;

import com.ugly.blog.constant.HttpStatus;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.dto.Page;
import com.ugly.blog.dto.TableDataInfo;

/**
 * @author JwZheng
 * @date 2021/4/9 22:38
 */
public class BaseController {

    /**
     * 返回表格分页数据
     *
     * @param page 数据库查询到的分页
     * @return 分页数据
     */
    protected TableDataInfo getDataTable(Page<?> page) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setCount(page.getTotalCount());
        rspData.setData(page.getItems());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result) {
        return result ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * @return 成功消息
     */
    protected AjaxResult success() {
        return AjaxResult.success();
    }

    /**
     * @param msg 消息
     * @return 成功消息
     */
    protected AjaxResult success(String msg) {
        return AjaxResult.success(msg);
    }

    /**
     * @return 失败消息
     */
    protected AjaxResult error() {
        return AjaxResult.error();
    }

    /**
     * @param msg 消息
     * @return 失败消息
     */
    protected AjaxResult error(String msg) {
        return AjaxResult.error(msg);
    }


}
