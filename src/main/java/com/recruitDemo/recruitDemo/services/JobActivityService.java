package com.recruitDemo.recruitDemo.services;

import com.recruitDemo.recruitDemo.dto.JobActivityDTO;
import com.recruitDemo.recruitDemo.entity.*;
import com.recruitDemo.recruitDemo.mapper.JobActivityMapper;
import com.recruitDemo.recruitDemo.repository.JobActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JobActivityService {
    private final JobActivityRepository jobActivityRepository;
    private final JobActivityMapper jobActivityMapper;

    @Autowired
    public JobActivityService(JobActivityRepository jobActivityRepository, JobActivityMapper jobActivityMapper) {
        this.jobActivityRepository = jobActivityRepository;
        this.jobActivityMapper = jobActivityMapper;
    }

    public JobActivity addNew(JobActivity jobActivity){
        return jobActivityRepository.save(jobActivity);
    }


    public List<RecruiterJobsDto> getRecruiterJobs(int recruiter) {

        List<IRecruiterJobs> recruiterJobsDtos = jobActivityRepository.getRecruiterJobs(recruiter);

        List<RecruiterJobsDto> recruiterJobsDtoList = new ArrayList<>();

        for (IRecruiterJobs rec : recruiterJobsDtos) {
            JobLocation loc = new JobLocation(rec.getLocationId(), rec.getCity(), rec.getState(), rec.getCountry());
            JobCompany comp = new JobCompany(rec.getCompanyId(), rec.getName(), "");
            recruiterJobsDtoList.add(new RecruiterJobsDto(rec.getTotalCandidates(), rec.getJob_post_id(),
                    rec.getJob_title(), loc, comp));
        }
        return recruiterJobsDtoList;

    }

    public JobActivity getOne(int id) {
        return jobActivityRepository.findById(id).orElseThrow(()->new RuntimeException("Job not found."));
    }

    public void removeJob(int id){
        jobActivityRepository.deleteById(id);
    }

    public List<JobActivity> getAll() {
        return jobActivityRepository.findAll();
    }

    public List<JobActivity> search(String job, String location, List<String> type, List<String> remote, LocalDate searchDate) {
        return Objects.isNull(searchDate)?jobActivityRepository.searchWithoutDate(job,location,remote,type):
                jobActivityRepository.search(job,location,remote,type,searchDate);
    }


    public JobActivityDTO getOneRest(int id) {

        return jobActivityMapper.apply(jobActivityRepository.findById(id).orElseThrow(()->new RuntimeException("Job not found.")));
    }
}
