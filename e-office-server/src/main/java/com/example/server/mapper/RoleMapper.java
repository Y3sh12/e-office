package com.example.server.mapper;

import com.example.server.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dengyinshan
 * @since 2021-10-31
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * @author dengyinshan 2021/11/3 22:17
     * @description 根据用户ID查询角色列表
     */
    List<Role> getRoles(Integer id);
}
