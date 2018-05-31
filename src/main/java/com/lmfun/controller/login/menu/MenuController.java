package com.lmfun.controller.login.menu;

import com.alibaba.fastjson.JSONObject;
import com.lmfun.common.util.ResultBuilderUtils;
import com.lmfun.pojo.vo.menu.ModelMenu;
import com.lmfun.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 没想法的岁月 on 2018/5/31.
 */
@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("menu/listAllMenu")
    @ResponseBody
    public String listMenu(@RequestBody String payload) {
        JSONObject jsonObject = JSONObject.parseObject(payload);
        //
        List<ModelMenu> modelMenulist= menuService.listAllmenu();
        return ResultBuilderUtils.buildSuccess(modelMenulist);
    }
}
