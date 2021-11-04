package com.example.server.controller;


import com.example.server.pojo.JobLevel;
import com.example.server.pojo.RespBean;
import com.example.server.service.IJobLevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dengyinshan
 * @since 2021-10-31
 */
@RestController
@RequestMapping("/system/basic/jobLevel")
public class JobLevelController {

    @Autowired
    private IJobLevelService jobLevelService;

    /**
     * 查询所有职称
     * @return
     */
    @ApiOperation(value = "获取所有职称")
    @GetMapping("/")
    public List<JobLevel> getAllJobLevels(){
        return jobLevelService.list();
    }

    /**
     * 添加职称
     * @param jobLevel
     * @return
     */
    @ApiOperation(value = "添加职称")
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel){
        jobLevel.setCreateDate(LocalDateTime.now());
        if (jobLevelService.save(jobLevel)) {
            return RespBean.success("职称添加成功！");
        }
        return RespBean.error("职称添加失败！");
    }

    /**
     * 更新职称
     * @param jobLevel
     * @return
     */
    @ApiOperation(value = "更新职称")
    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody JobLevel jobLevel){
        if (jobLevelService.updateById(jobLevel)) {
            return RespBean.success("职称更新成功！");
        }
        return RespBean.error("职称更新失败！");
    }

    /**
     * 根据id删除职称
     * @param id
     * @return
     */
    @ApiOperation(value = "删除职称")
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable Integer id){
        if (jobLevelService.removeById(id)) {
            return RespBean.success("职称删除成功！");
        }
        return RespBean.error("职称删除失败！");
    }

    /**
     * 批量删除职称
     * @param ids
     * @return
     */
    @ApiOperation(value = "批量删除职称")
    @DeleteMapping("/")
    public RespBean deleteJobLevelsByIds(Integer[] ids){
        if (jobLevelService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("职称批量删除成功！");
        }
        return RespBean.error("职称批量删除失败！");
    }

}
