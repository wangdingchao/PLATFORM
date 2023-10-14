package com.example.platform.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.platform.exception.BizException;
import com.example.platform.pojo.GameEvent;
import com.example.platform.dto.JsonResult;
import com.example.platform.service.GameEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wdc
 * @date 2023/10/10
 * @description
 */

@RestController
@RequestMapping("/gameEvent")
public class GameEventController {

    @Autowired
    private GameEventService gameEventService;


//    @RequestMapping(value = "/allPage", method = RequestMethod.GET)
//    public JsonResult allPage(int page,int size) throws BizException {
//        Page<GameEvent> gameEventPage = gameEventService.findPage(page, size);
//        return JsonResult.success(gameEventPage);
//    }
//
//    @RequestMapping(value = "/addGameEvent", method = RequestMethod.POST)
//    public JsonResult addGameEvent(@RequestBody GameEvent gameEvent) {
//        gameEventService.addGameEvent(gameEvent);
//        return JsonResult.success();
//    }
//
//    @RequestMapping(value = "updateGameEvent", method = RequestMethod.PUT)
//    public JsonResult updateGameEvent(@RequestBody GameEvent gameEvent) {
//        gameEventService.updateGameEventById(gameEvent);
//        return JsonResult.success();
//    }
//
//    @RequestMapping(value = "deleteGameEvent", method = RequestMethod.DELETE)
//    public JsonResult deleteGameEvent(Integer id) {
//        gameEventService.deleteGameEventById(id);
//        return JsonResult.success();
//    }
//
//    @RequestMapping(value = "/findById", method = RequestMethod.GET)
//    public JsonResult findById(Integer id) {
//        GameEvent gameEvent = gameEventService.findById(id);
//        return JsonResult.success(gameEvent);
//    }
//
//    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
//    public JsonResult findAll() {
//        List<GameEvent> all = gameEventService.findAll();
//        return JsonResult.success(all);
//    }


}
