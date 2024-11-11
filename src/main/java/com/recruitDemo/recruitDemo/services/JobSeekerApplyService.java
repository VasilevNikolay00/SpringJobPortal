package com.recruitDemo.recruitDemo.services;

import com.recruitDemo.recruitDemo.entity.JobActivity;
import com.recruitDemo.recruitDemo.entity.JobSeekerApply;
import com.recruitDemo.recruitDemo.entity.JobSeekerProfile;
import com.recruitDemo.recruitDemo.repository.JobSeekerApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerApplyService {

    private final JobSeekerApplyRepository jobSeekerApplyRepository;

    @Autowired
    public JobSeekerApplyService(JobSeekerApplyRepository jobSeekerApplyRepository) {
        this.jobSeekerApplyRepository = jobSeekerApplyRepository;
    }

    public List<JobSeekerApply> getCandidatesJobs(JobSeekerProfile userAccountId) {
        return jobSeekerApplyRepository.findByUserId(userAccountId);
    }

    public List<JobSeekerApply> getJobCandidates(JobActivity job) {
        return jobSeekerApplyRepository.findByJob(job);
    }

    public void addNew(JobSeekerApply jobSeekerApply) {
        jobSeekerApplyRepository.save(jobSeekerApply);
    }

    public void removeByJob(JobActivity jobActivity) {
        List<JobSeekerApply> jobSeekerApplyList = jobSeekerApplyRepository.findByJob(jobActivity);
        jobSeekerApplyRepository.deleteAll(jobSeekerApplyList);
    }

    public void removeByUser(JobSeekerProfile jobSeekerProfile) {
        List<JobSeekerApply> jobSeekerApplyList = jobSeekerApplyRepository.findByUserId(jobSeekerProfile);
        jobSeekerApplyRepository.deleteAll(jobSeekerApplyList);
    }
}