package com.recruitDemo.recruitDemo.repository;

import com.recruitDemo.recruitDemo.entity.JobCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<JobCompany,Integer> {
}
