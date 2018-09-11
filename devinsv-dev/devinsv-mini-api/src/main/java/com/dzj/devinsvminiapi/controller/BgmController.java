package com.dzj.devinsvminiapi.controller;

import com.dzj.BgmService;
import com.dzj.pojo.Bgm;
import com.dzj.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Devin13 on 2018/8/17.
 * @version 1.0
 */
@RestController
@RequestMapping("/bgm")
public class BgmController {

    @Autowired
    private BgmService bgmService;

    @PostMapping("/list")
    public JsonResult list(){
        return JsonResult.ok(bgmService.queryBgmList());
    }
}