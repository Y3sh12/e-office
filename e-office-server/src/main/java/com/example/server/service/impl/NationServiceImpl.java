package com.example.server.service.impl;

import com.example.server.pojo.Nation;
import com.example.server.mapper.NationMapper;
import com.example.server.service.INationService;
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
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {

}
