package com.recruitDemo.recruitDemo.mapper;

import com.recruitDemo.recruitDemo.dto.JobCompanyDTO;
import com.recruitDemo.recruitDemo.entity.JobCompany;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class JobCompanyMapper implements Function<JobCompany,JobCompanyDTO> {

    @Override
    public JobCompanyDTO apply(JobCompany jobCompany) {
        return new JobCompanyDTO(
                jobCompany.getId(),
                jobCompany.getLogo(),
                jobCompany.getName()
        );
    }

    public JobCompany apply(JobCompanyDTO jobCompanyDTO) {

        JobCompany entity = new JobCompany();
        entity.setId(jobCompanyDTO.id());
        entity.setName(jobCompanyDTO.name());
        entity.setLogo(jobCompanyDTO.logo());
        return entity;
    }
}
