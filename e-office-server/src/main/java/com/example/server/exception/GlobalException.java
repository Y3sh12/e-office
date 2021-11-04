package com.example.server.exception;

import com.example.server.pojo.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author dengyinshan
 * @version 1.0.0
 * @createTime 2021/11/4 23:42
 * @Description 全局异常处理
 */

// 控制器的增强类
@RestControllerAdvice
public class GlobalException {

    //异常处理器
    @ExceptionHandler(SQLException.class)
    public RespBean mySqlException(SQLException e){
        if (e instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("该数据有关联数据，操作失败");
        }
        return RespBean.error("数据库异常，操作失败");
    }
}
