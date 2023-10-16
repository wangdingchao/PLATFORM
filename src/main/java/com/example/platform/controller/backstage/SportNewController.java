package com.example.platform.controller.backstage;

//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.platform.dto.JsonResult;
import com.example.platform.exception.BizException;
import com.example.platform.pojo.SportNew;
import com.example.platform.service.SportNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Shuai Fang
 * @version 1.0
 * @description 新闻页面展示Controller，用与页面上新闻展示和管理。
 * @date 2023/10/12 16:45
 */
@RestController
@RequestMapping("/sportNew")
public class SportNewController {

    @Autowired
    private SportNewService sportNewService;

    // 分页展示页面, 默认从第一页开始展示，一页展示十条信息。
    // Page display, default from the first page display, one page display ten news information.
//    @RequestMapping(value = "/allPage", method = RequestMethod.GET)
//    public JsonResult allPage(@RequestParam(defaultValue = "1") int page,
//                              @RequestParam(defaultValue = "10") int size) throws BizException {
//        Page<SportNew> sportNewPage = sportNewService.findSportNewPage(page, size);
//        return JsonResult.success(sportNewPage);
//    }

    // 在页面添加一个体育新闻
    // Adding an athlete on the page
//    @RequestMapping(value = "/addSportNew", method = RequestMethod.POST)
//    public JsonResult addAthlete(@RequestBody SportNew sportNew) {
//        sportNewService.addSportNew(sportNew);
//        return JsonResult.success();
//    }

    // 在页面上更新运动员
    // Update a sport new on the page
//    @RequestMapping(value = "updateSportNew", method = RequestMethod.PUT)
//    public JsonResult updateSportNew(@RequestBody SportNew sportNew) {
//        sportNewService.updateSportNewById(sportNew);
//        return JsonResult.success();
//    }
//
//    // 在页面上删除体育新闻
//    // Delete a sport new on the page
//    @RequestMapping(value = "deleteSportNew", method = RequestMethod.DELETE)
//    public JsonResult deleteSportNew(Integer aid) {
//        sportNewService.deleteSportNewById(aid);
//        return JsonResult.success();
//    }
//
//    // 在页面上找到一条新闻
//    // Query a sport new by id
//    @RequestMapping(value = "/findSportNewById", method = RequestMethod.GET)
//    public JsonResult findSportNewById(Integer snId) {
//        SportNew sportNew = sportNewService.findSportNewById(snId);
//        return JsonResult.success(sportNew);
//    }
//
//    // 展示所有新闻
//    // Paging all news
//    @RequestMapping(value = "/findAllSportNew", method = RequestMethod.GET)
//    public JsonResult findAllSportNew() {
//        List<SportNew> all = sportNewService.findAllSportNew();
//        return JsonResult.success(all);
//    }

}
