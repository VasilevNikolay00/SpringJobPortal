package com.recruitDemo.recruitDemo.repository;


import com.recruitDemo.recruitDemo.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserTypeRepository extends JpaRepository<UserType,Integer> {
}
