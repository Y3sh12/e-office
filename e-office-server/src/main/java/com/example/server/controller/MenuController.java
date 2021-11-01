package com.example.server.controller;


import com.example.server.pojo.Menu;
import com.example.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器 系统管理
 * </p>
 *
 * @author dengyinshan
 * @since 2021-10-31
 */
@RestController
@RequestMapping("/system/manage")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation("根据当前用户获取菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenusByAdminId(){
        return menuService.getMenusByAdminId();
    }

}
