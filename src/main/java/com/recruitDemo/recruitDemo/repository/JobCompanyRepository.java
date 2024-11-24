package com.recruitDemo.recruitDemo.repository;

import com.recruitDemo.recruitDemo.entity.JobCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCompanyRepository extends JpaRepository<JobCompany,Integer> {
   JobCompany findByName(String name);
}
