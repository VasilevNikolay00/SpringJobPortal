package com.recruitDemo.recruitDemo.mapper;
import com.recruitDemo.recruitDemo.dto.JobLocationDTO;
import com.recruitDemo.recruitDemo.entity.JobLocation;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class JobLocationMapper implements Function<JobLocation, JobLocationDTO> {


    @Override
    public JobLocationDTO apply(JobLocation jobLocation) {
        return new JobLocationDTO(
        jobLocation.getCity(),
        jobLocation.getState(),
        jobLocation.getCountry());
    }

    public JobLocation apply(JobLocationDTO jobLocationDTO){
        JobLocation location = new JobLocation();
        location.setCity(jobLocationDTO.city());
        location.setCountry(jobLocationDTO.country());
        location.setState(jobLocationDTO.state());
        return location;
    }
}
