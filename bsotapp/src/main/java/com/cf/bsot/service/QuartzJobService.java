package com.cf.bsot.service;

import com.cf.bsot.common.quartz.QuartzManager;
import com.cf.bsot.mapper.QuartzJobMapper;
import com.cf.bsot.model.pojo.QuartzJob;
import com.cf.bsot.model.pojo.User;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cf
 * @version 1.0
 * @date 2020/10/12 23:36
 */
@Service
public class QuartzJobService {

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @Autowired
    private QuartzManager quartzManager;

    public List<QuartzJob> getAllOpenQuartzJobInfo(){
        return quartzJobMapper.getJobListByOpenStatus();
    }

    public QuartzJob getQuartzJobInfoById(Long id) {
        return quartzJobMapper.getQuartzJobInfoByPrimaryKey(id);
    }


    public Integer addQuartzJobInfo(QuartzJob quartzJob) {
        return quartzJobMapper.addQuartzJobInfo(quartzJob);
    }

    public Integer deleteQuartzJobInfoById(Long id) {
        return quartzJobMapper.deleteQuartzJobInfoByPrimaryKey(id);
    }

    public Integer updateQuartzJobInfoById(QuartzJob quartzJob) {
        return quartzJobMapper.updateQuartzJobInfoSelective(quartzJob);
    }

    public List<QuartzJob> pageListQuartzJobsInfo(Integer page, Integer size, QuartzJob searchQuartzJob) {
        PageHelper.startPage(page,size);
        return quartzJobMapper.getJobPageList(searchQuartzJob);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRunningJob(Long id) {
        quartzManager.deleteJob(id);
        quartzJobMapper.setStatusStop(id);
        return quartzManager.checkJobExists(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean runningQuartzJob(Long id) {
        QuartzJob jobInfo = this.getQuartzJobInfoById(id);
        quartzJobMapper.setStatusRun(id);
        quartzManager.resumeJob(jobInfo);
        return quartzManager.checkJobExists(id);
    }
}
