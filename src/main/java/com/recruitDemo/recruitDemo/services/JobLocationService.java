package com.recruitDemo.recruitDemo.services;

import com.recruitDemo.recruitDemo.dto.JobLocationDTO;
import com.recruitDemo.recruitDemo.entity.JobLocation;
import com.recruitDemo.recruitDemo.mapper.JobLocationMapper;
import com.recruitDemo.recruitDemo.repository.JobLocationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class JobLocationService {
    private final JobLocationRepository locationRepository;
    private final JobLocationMapper jobLocationMapper;

    @Autowired
    public JobLocationService(JobLocationRepository locationRepository, JobLocationMapper jobLocationMapper) {
        this.locationRepository = locationRepository;
        this.jobLocationMapper = jobLocationMapper;
    }

    public JobLocationDTO getJobLocationDTO(JobLocationDTO jobLocationDTO){

        return jobLocationMapper.apply(locationRepository.search(jobLocationDTO.city(),jobLocationDTO.state(),jobLocationDTO.country()));
    }

    public JobLocationDTO getJobLocationDTO(String city, String state, String country){

        return jobLocationMapper.apply(locationRepository.search(city,state,country));
    }

    public JobLocation getJobLocation(JobLocationDTO jobLocationDTO){

        return locationRepository.search(jobLocationDTO.city(),jobLocationDTO.state(),jobLocationDTO.country());
    }

    public JobLocation getJobLocation(String city, String state, String country){

        return locationRepository.search(city,state,country);
    }
}
