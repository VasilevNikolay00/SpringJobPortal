package com.recruitDemo.recruitDemo.services;

import com.recruitDemo.recruitDemo.dto.JobCompanyDTO;
import com.recruitDemo.recruitDemo.entity.JobCompany;
import com.recruitDemo.recruitDemo.mapper.JobCompanyMapper;
import com.recruitDemo.recruitDemo.repository.JobCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobCompanyService {
    private final JobCompanyRepository jobCompanyRepository;
    private final JobCompanyMapper jobCompanyMapper;

    public JobCompanyService(JobCompanyRepository jobCompanyRepository, JobCompanyMapper jobCompanyMapper) {
        this.jobCompanyRepository = jobCompanyRepository;
        this.jobCompanyMapper = jobCompanyMapper;
    }

    JobCompanyDTO getCompanyDto(String name){
        return jobCompanyMapper.apply(jobCompanyRepository.findByName(name));
    }

    JobCompany getCompanyEntity(String name){
        return jobCompanyRepository.findByName(name);
    }
}
