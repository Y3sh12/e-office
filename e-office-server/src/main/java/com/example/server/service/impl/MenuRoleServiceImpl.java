package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.server.pojo.MenuRole;
import com.example.server.mapper.MenuRoleMapper;
import com.example.server.pojo.RespBean;
import com.example.server.service.IMenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dengyinshan
 * @since 2021-10-31
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;


    /**
     * @author dengyinshan 2021/11/7 23:02
     * @description 更新角色菜单
     */
    @Override
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        // 1.清空该角色对应的所有菜单
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        // 2.更新该角色的菜单列表
        if(null == mids || 0 == mids.length) {
            return RespBean.success("角色菜单无更新项");
        }
        Integer result = menuRoleMapper.insertRecord(rid,mids);
        if(result == mids.length) {
            return RespBean.success("角色菜单更新成功");
        }
        return RespBean.error("角色菜单更新失败");
    }
}
