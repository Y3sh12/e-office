package com.example.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.server.pojo.*;
import com.example.server.service.IMenuRoleService;
import com.example.server.service.IMenuService;
import com.example.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dengyinshan
 * @version 1.0.0
 * @createTime 2021/11/7 22:14
 * @Description 权限组
 */
@RestController
@RequestMapping("/system/basic/permission")
public class PermissionController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
        if(!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_"+role.getName());
        }
        if (roleService.save(role)) {
            return RespBean.success("角色添加成功！");
        }
        return RespBean.error("角色添加失败！");
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRole(@PathVariable Integer rid) {
        if (roleService.removeById(rid)) {
            return RespBean.success("角色删除成功！");
        }
        return RespBean.error("角色删除失败！");
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @ApiOperation(value = "根据角色ID查询菜单ID")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMenuIdByRoleId(@PathVariable Integer rid) {
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid",rid)).stream().map(MenuRole::getMid).collect(Collectors.toList());
    }

    @ApiOperation(value = "更新角色菜单")
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid,Integer[] mids) {
        return menuRoleService.updateMenuRole(rid,mids);
    }
}
