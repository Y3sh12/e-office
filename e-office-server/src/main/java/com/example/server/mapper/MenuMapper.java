package com.example.server.mapper;

import com.example.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dengyinshan
 * @since 2021-10-31
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据当前登录用户获取菜单
     * @param id
     * @return
     */
    List<Menu> getMenusByAdminId(@Param("id") Integer id);
}
