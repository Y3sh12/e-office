package com.example.server.config.security;

import com.example.server.pojo.Admin;
import com.example.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author dengyinshan
 * @version 1.0.0
 * @createTime 2021/10/31 23:09
 * @Description SpringSecurity配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 使用了JWT，不需要csrf
        http.csrf().disable()
                // 基于token，不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/login/**", "/logout/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                // 禁用缓存
                .headers()
                .cacheControl();

        // jwt登录授权拦截器
        http.addFilterBefore(jwtAuthorizationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                // 异常处理 自定义未授权和未登录结果返回
                .exceptionHandling()
                // 403
                .accessDeniedHandler(restfulAccessDeniedHandler)
                // 401
                .authenticationEntryPoint(restAuthorizationEntryPoint);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter() {
        return new JwtAuthorizationTokenFilter();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        //获取用户登录信息
        return username -> {
            Admin admin = adminService.getUserByUsername(username);
            if (null != admin) {
                return admin;
            }
            return null;
        };
    }
}
