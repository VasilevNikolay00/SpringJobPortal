package com.recruitDemo.recruitDemo.repository;

import com.recruitDemo.recruitDemo.entity.JobLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobLocationRepository extends JpaRepository<JobLocation,Integer> {

    @Query(value = "SELECT * FROM job_location " +
            "WHERE city LIKE %:city% " +
            "AND state LIKE %:state% " +
            "AND country LIKE %:country%", nativeQuery = true)
    JobLocation search(@Param("city") String city,
                       @Param("state") String state,
                       @Param("country") String country);

}
