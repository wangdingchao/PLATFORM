package com.example.platform.controller.backstage;

import com.example.platform.dto.JsonResult;
import com.example.platform.exception.BizException;

import com.example.platform.pojo.Athlete;
import com.example.platform.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Shuai Fang
 * @version 1.0
 * @description 运动员controller，用来页面对运动员进行显示和增删改查
 * @date 2023/10/12 16:45
 */

@RestController
@RequestMapping("/athlete")
public class AthleteController {
    @Autowired
    private AthleteService athleteService;

    // 分页展示页面, 默认从第一页开始展示，一页展示十条信息。
    // Page display, default from the first page display, one page display ten athletes information.
//    @RequestMapping(value = "/allPage", method = RequestMethod.GET)
//    public JsonResult<Page<Athlete>> allPage(@RequestParam(defaultValue = "1") int page,
//                                             @RequestParam(defaultValue = "10") int size) throws BizException {
//        Page<Athlete> athletePage = athleteService.findAthletePage(page, size);
//        return JsonResult.success(athletePage);
//    }
//
//    // 在页面添加一个运动员
//    // Adding an athlete on the page
//    @RequestMapping(value = "/addAthlete", method = RequestMethod.POST)
//    public JsonResult addAthlete(@RequestBody Athlete athlete) {
//        athleteService.addAthlete(athlete);
//        return JsonResult.success();
//    }
//
//    // 在页面上更新运动员
//    // Update an athlete on the page
//    @RequestMapping(value = "updateAthlete", method = RequestMethod.PUT)
//    public JsonResult updateAthlete(@RequestBody Athlete athlete) {
//        athleteService.updateAthleteById(athlete);
//        return JsonResult.success();
//    }
//
//    // 在页面上删除运动员
//    // Delete an athlete on the page
//    @RequestMapping(value = "deleteAthlete", method = RequestMethod.DELETE)
//    public JsonResult deleteAthlete(Integer aid) {
//        athleteService.deleteAthleteById(aid);
//        return JsonResult.success();
//    }
//
//    // 在页面上找到一个运动员
//    // Query an athlete by id
//    @RequestMapping(value = "/findAthleteById", method = RequestMethod.GET)
//    public JsonResult findAthleteById(Integer aid) {
//        Athlete athlete = athleteService.findAthleteById(aid);
//        return JsonResult.success(athlete);
//    }
//    // 展示所有运动员
//    // Paging all athlete
//    @RequestMapping(value = "/findAllAthlete", method = RequestMethod.GET)
//    public JsonResult findAllAthlete() {
//        List<Athlete> all = athleteService.findAllAthlete();
//        return JsonResult.success(all);
//    }

}
