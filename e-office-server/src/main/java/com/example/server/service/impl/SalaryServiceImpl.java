package com.example.server.service.impl;

import com.example.server.pojo.Salary;
import com.example.server.mapper.SalaryMapper;
import com.example.server.service.ISalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {

}
