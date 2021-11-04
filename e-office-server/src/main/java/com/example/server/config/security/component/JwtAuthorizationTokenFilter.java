package com.example.server.config.security.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dengyinshan
 * @version 1.0.0
 * @createTime 2021/10/31 23:26
 * @Description Jwt登录授权拦截器
 */
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头
        String authHeader = request.getHeader(tokenHeader);
        // 判断令牌是否存在并且正确
        if (null != authHeader && authHeader.startsWith(tokenHead)) {
            // 获取令牌
            String token = authHeader.substring(tokenHead.length());
            // 根据令牌获取用户名
            String username = jwtTokenUtil.getUserNameFromToken(token);
            // 如果用户名不为空但是认证对象为空（未登录）
            if (null != username && null == SecurityContextHolder.getContext().getAuthentication()) {
                // 根据用户名获取用户对象
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 判断token是否有效，重置用户对象
                if (jwtTokenUtil.validateToken(token, userDetails)) {
                    // 给SpringSecurity全局上下文设置当前登录用户对象
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    WebAuthenticationDetails webAuthenticationDetails = new WebAuthenticationDetailsSource().buildDetails(request);
                    authenticationToken.setDetails(webAuthenticationDetails);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        // 放行
        filterChain.doFilter(request,response);
    }
}
