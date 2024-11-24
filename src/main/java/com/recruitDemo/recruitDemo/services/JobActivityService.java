package com.recruitDemo.recruitDemo.services;

import com.recruitDemo.recruitDemo.dto.JobActivityDTO;
import com.recruitDemo.recruitDemo.dto.JobPostCreateDTO;
import com.recruitDemo.recruitDemo.dto.SearchJobActivityDTO;
import com.recruitDemo.recruitDemo.entity.*;
import com.recruitDemo.recruitDemo.mapper.JobActivityMapper;
import com.recruitDemo.recruitDemo.repository.JobActivityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class JobActivityService {
    private final JobActivityRepository jobActivityRepository;
    private final JobActivityMapper jobActivityMapper;
    private final JobSeekerApplyService jobSeekerApplyService;
    private final JobSeekerSaveService jobSeekerSaveService;
    private final UserService userService;
    private final JobLocationService jobLocationService;
    private final JobCompanyService jobCompanyService;


    @Autowired
    public JobActivityService(JobActivityRepository jobActivityRepository, JobActivityMapper jobActivityMapper, JobSeekerApplyService jobSeekerApplyService, JobSeekerSaveService jobSeekerSaveService, UserService userService, JobLocationService jobLocationService, JobCompanyService jobCompanyService) {
        this.jobActivityRepository = jobActivityRepository;
        this.jobActivityMapper = jobActivityMapper;
        this.jobSeekerApplyService = jobSeekerApplyService;
        this.jobSeekerSaveService = jobSeekerSaveService;
        this.userService = userService;
        this.jobLocationService = jobLocationService;
        this.jobCompanyService = jobCompanyService;
    }

    public JobActivity addNew(JobActivity jobActivity){
        return jobActivityRepository.save(jobActivity);
    }

    public void addNew(JobPostCreateDTO dto){
        JobActivity job = new JobActivity();
        JobCompany company = jobCompanyService.getCompanyEntity(dto.companyName());
        JobLocation location = jobLocationService.getJobLocation(dto.city(),dto.state(),dto.country());

        if(Objects.isNull(company)){
            company = new JobCompany();
            company.setName(dto.companyName());
            job.setJobCompanyId(company);
        }else{
            job.setJobCompanyId(company);
        }
        System.out.println(location);
        if(Objects.isNull(location)){
            location = new JobLocation();
            location.setCity(dto.city());
            location.setState(dto.state());
            location.setCountry(dto.country());

            job.setJobLocationId(location);
        }else{
            job.setJobLocationId(location);
        }

        job.setPostedById(userService.findByEmail(dto.userId()));
        job.setJobTitle(dto.jobTitle());
        job.setRemote(dto.remote());
        job.setJobType(dto.jobType());
        job.setDescription(dto.description());
        job.setSalary(dto.salary());
        job.setPostedDate(new Date());

        jobActivityRepository.save(job);
    }

    public JobActivity addNew(JobActivityDTO dto , int id){
        JobActivity jobActivity = getOne(id);

        if (!(dto.jobTitle() ==null)){
            jobActivity.setJobTitle(dto.jobTitle());
        }

        if (!(dto.description() ==null)){
            jobActivity.setDescription(dto.description());
        }

        if (!(dto.salary() ==null)){
            jobActivity.setSalary(dto.salary());
        }

        if (!(dto.jobType() ==null)){
            jobActivity.setJobType(dto.jobType());
        }

        if (!(dto.remote() ==null)){
            jobActivity.setRemote(dto.remote());
        }

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
        jobSeekerApplyService.removeByJob(getOne(id));
        jobSeekerSaveService.removeByJob(getOne(id));
        jobActivityRepository.deleteById(id);
    }

    public List<JobActivity> getAll() {
        return jobActivityRepository.findAll();
    }


    public JobActivityDTO getOneRest(Integer id) {
        return jobActivityMapper.apply(jobActivityRepository.findById(id).orElseThrow(()->new EntityNotFoundException((id.toString()))));
    }

    public List<JobActivityDTO> getAllRest() {
        return jobActivityRepository.findAll().stream().map(jobActivityMapper).toList();
    }

    public List<JobActivity> search(String job, String location, List<String> type, List<String> remote, LocalDate searchDate) {

        return Objects.isNull(searchDate)?jobActivityRepository.searchWithoutDate(job,location,remote,type):
                jobActivityRepository.search(job,location,remote,type,searchDate);
    }


    public List<JobActivityDTO> search(SearchJobActivityDTO dto){

        return (Objects.isNull(dto.createdAt())?jobActivityRepository.searchWithoutDate(dto.jobTitle(),dto.location(),dto.jobType(),dto.remote()):
                jobActivityRepository.search(dto.jobTitle(),dto.location(),dto.remote(),dto.jobType(),dto.createdAt())).stream().map(jobActivityMapper).toList();
    }
}
