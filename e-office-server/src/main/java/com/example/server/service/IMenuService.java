package com.example.server.service;

import com.example.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dengyinshan
 * @since 2021-10-31
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 根据当前用户ID获取菜单列表
     * @return
     */
    List<Menu> getMenusByAdminId();

}
