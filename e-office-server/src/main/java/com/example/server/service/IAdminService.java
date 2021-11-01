package com.example.server.service;

import com.example.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dengyinshan
 * @since 2021-10-31
 */
public interface IAdminService extends IService<Admin> {

    RespBean login(String username, String password, HttpServletRequest request);

    Admin getUserByUsername(String username);
}

