package com.recruitDemo.recruitDemo.repository;

import com.recruitDemo.recruitDemo.entity.RecruiterProfile;
import com.recruitDemo.recruitDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RecruiterProfileRepository extends JpaRepository<RecruiterProfile,Integer> {
}
