package com.example.server.mapper;

import com.example.server.pojo.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dengyinshan
 * @since 2021-10-31
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * @author dengyinshan 2021/11/7 23:07
     * @description 批量更新该角色对应的菜单列表
     */
    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
