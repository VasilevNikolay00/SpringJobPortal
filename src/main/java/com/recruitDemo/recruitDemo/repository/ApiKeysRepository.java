package com.recruitDemo.recruitDemo.repository;

import com.recruitDemo.recruitDemo.entity.ApiKey;

import com.recruitDemo.recruitDemo.entity.JobSeekerSave;
import com.recruitDemo.recruitDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiKeysRepository extends JpaRepository<ApiKey,Integer> {
    List<ApiKey> findByUserId(User user);

}
