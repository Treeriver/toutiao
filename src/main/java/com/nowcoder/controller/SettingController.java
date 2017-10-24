package com.nowcoder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/6/22.
 */
@Controller
public class SettingController {
    @RequestMapping("/setting")
    @ResponseBody
    public String stettig(){
        return "Setting:OK";
    }
}
