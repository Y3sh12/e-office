package com.example.server.service.impl;

import com.example.server.pojo.Department;
import com.example.server.mapper.DepartmentMapper;
import com.example.server.service.IDepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
