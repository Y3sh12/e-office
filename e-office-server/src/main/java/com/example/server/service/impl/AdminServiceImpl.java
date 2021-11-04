package com.example.server.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.server.config.security.component.JwtTokenUtil;
import com.example.server.mapper.RoleMapper;
import com.example.server.pojo.Admin;
import com.example.server.mapper.AdminMapper;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.Role;
import com.example.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dengyinshan
 * @since 2021-10-31
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private RoleMapper roleMapper;

    /**
     * @author dengyinshan 2021/10/31 22:01
     * @description 登录后返回token
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        // 获取验证码内容
        String captcha = (String) request.getSession().getAttribute("captcha");
        // 忽略大小写进行比对
        if (StringUtils.isBlank(code) || !captcha.equals(code)) {
            return RespBean.error("验证码错误，请重新输入！");
        }

        // 1.登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // 判断用户是否存在
        if (null==userDetails||!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new UsernameNotFoundException("用户名或密码不正确！");
        }
        // 判断用户是否启用
        if (!userDetails.isEnabled()){
            return RespBean.error("账号无法使用，请联系管理员！");
        }

        // 2.更新security登录用户对象
        // 将当前用户对象存入SpringSecurity全局上下文，方便其他方法使用
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 3.生成token并返回
        String token = jwtTokenUtil.generatorToken(userDetails);
        Map<String,Object> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return RespBean.success("登录成功",tokenMap);
    }

    /**
     * @author dengyinshan 2021/10/31 22:30
     * @description 根据用户名获取用户
     */
    @Override
    public Admin getUserByUsername(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username).eq("enabled",true));
    }

    /**
     * @author dengyinshan 2021/11/3 22:17
     * @description 根据用户ID查询角色列表
     */
    @Override
    public List<Role> getRoles(Integer id) {
        return roleMapper.getRoles(id);
    }

}
