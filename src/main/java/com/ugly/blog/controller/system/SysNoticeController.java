package com.ugly.blog.controller.system;

import com.github.pagehelper.PageHelper;
import com.ugly.blog.constant.PageConstant;
import com.ugly.blog.controller.BaseController;
import com.ugly.blog.domain.Notice;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.service.NoticeService;
import com.ugly.blog.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/27 14:48
 */
@Controller
@RequestMapping("api/system/notice")
public class SysNoticeController extends BaseController {

    private final NoticeService noticeService;

    @Autowired
    public SysNoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }


    @ResponseBody
    @RolesAllowed("admin")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public AjaxResult getTableData(@RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_INDEX) Integer pageIndex,
                                   @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        startPage(pageIndex, pageSize);
        PageHelper.orderBy("update_time desc");
        List<Notice> list = noticeService.getAllIsNotDel();
        return getDataTable(list);
    }


    @ResponseBody
    @RolesAllowed("user")
    @RequestMapping(value = "/list/simple", method = RequestMethod.GET)
    public AjaxResult getList() {
        PageHelper.startPage(1, PageConstant.NOTICE_NUM);
        PageHelper.orderBy("update_time desc");
        List<Notice> list = noticeService.getAllIsNotDel();
        return getDataTable(list);
    }


    @ResponseBody
    @RolesAllowed("admin")
    @RequestMapping(value = "/delete/{noticeId}", method = RequestMethod.PUT)
    public AjaxResult delete(@PathVariable("noticeId") Integer noticeId) {
        noticeService.checkAuthorized(noticeId);
        return toAjax(noticeService.delete(noticeId));
    }


    @ResponseBody
    @RolesAllowed("admin")
    @RequestMapping(value = "/updateOrInsert", method = RequestMethod.POST)
    public AjaxResult update(Notice notice) {
        if (Utils.isNull(notice.getNoticeId())) {
            return toAjax(noticeService.insert(notice));
        }
        noticeService.checkAuthorized(notice.getNoticeId());
        return toAjax(noticeService.update(notice));
    }
}
