package com.recruitDemo.recruitDemo.dto;

import com.recruitDemo.recruitDemo.entity.JobCompany;
import com.recruitDemo.recruitDemo.entity.JobLocation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record JobPostCreateDTO(
        @Valid
        @NotNull
        String jobTitle,

        @NotNull
        @Email
        String userId,

        @NotNull
        String city,

        @NotNull
        String country,

        String state,

        @NotNull
        String companyName,

        @NotNull
        String salary,

        @NotNull
        String jobType,

        @NotNull
        String remote,

        @NotNull
        String description


        ) {}
