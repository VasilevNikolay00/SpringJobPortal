package com.recruitDemo.recruitDemo.mapper;

import com.recruitDemo.recruitDemo.dto.JobActivityDTO;
import com.recruitDemo.recruitDemo.entity.JobActivity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class JobActivityMapper implements Function<JobActivity, JobActivityDTO> {
    @Override
    public JobActivityDTO apply(JobActivity jobActivity) {
        return new JobActivityDTO(
                jobActivity.getJobPostId(),
                jobActivity.getJobTitle(),
                jobActivity.getJobLocationId().getCity(),
                jobActivity.getJobLocationId().getCountry(),
                jobActivity.getJobCompanyId().getName(),
                jobActivity.getDescription(),
                jobActivity.getSalary(),
                jobActivity.getJobType(),
                jobActivity.getRemote(),
                jobActivity.getPostedDate()
                );
    }
}
