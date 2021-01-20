package com.cf.bsot.mapper;

import com.cf.bsot.model.pojo.QuartzJob;

import java.util.List;

/**
 * @author cf
 * @version 1.0
 * @date 2020/10/11 15:25
 */
public interface QuartzJobMapper {

    QuartzJob getQuartzJobInfoByPrimaryKey(Long id);

    Integer deleteQuartzJobInfoByPrimaryKey(Long id);

    Integer addQuartzJobInfo(QuartzJob quartzJobInfo);

    Integer updateQuartzJobInfoSelective(QuartzJob quartzJobInfo);

    List<QuartzJob> getJobListByOpenStatus();
}
