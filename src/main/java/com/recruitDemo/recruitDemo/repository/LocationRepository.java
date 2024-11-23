package com.recruitDemo.recruitDemo.repository;

import com.recruitDemo.recruitDemo.entity.JobLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<JobLocation,Integer> {
}
