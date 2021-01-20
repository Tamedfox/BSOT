package com.cf.bsot.service;

import com.cf.bsot.mapper.QuartzJobMapper;
import com.cf.bsot.model.pojo.QuartzJob;
import com.cf.bsot.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<User> pageListQuartzJobsInfo(Integer page, Integer size, QuartzJob searchQuartzJob) {
        return null;
    }
}
