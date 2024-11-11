package com.recruitDemo.recruitDemo.repository;
import com.recruitDemo.recruitDemo.entity.JobActivity;
import com.recruitDemo.recruitDemo.entity.JobSeekerProfile;
import com.recruitDemo.recruitDemo.entity.JobSeekerSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave, Integer> {

    public List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);

    List<JobSeekerSave> findByJob(JobActivity jobActivityId);

}