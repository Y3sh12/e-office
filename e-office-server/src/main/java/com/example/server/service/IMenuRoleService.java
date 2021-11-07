package com.example.server.service;

import com.example.server.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dengyinshan
 * @since 2021-10-31
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * @author dengyinshan 2021/11/7 23:01
     * @description 更新角色菜单
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
