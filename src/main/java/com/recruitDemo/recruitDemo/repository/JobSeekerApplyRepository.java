package com.recruitDemo.recruitDemo.repository;

import com.recruitDemo.recruitDemo.entity.JobActivity;
import com.recruitDemo.recruitDemo.entity.JobSeekerApply;
import com.recruitDemo.recruitDemo.entity.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerApplyRepository extends JpaRepository<JobSeekerApply,Integer> {

    List<JobSeekerApply> findByUserId(JobSeekerProfile userId);

    List<JobSeekerApply> findByJob(JobActivity job);

}
