package com.recruitDemo.recruitDemo.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record JobActivityDTO(

        @Valid

        Integer id,

        String jobTitle,

        String city,

        String country,

        String companyName,

        String description,

        String salary,

        String jobType,

        String remote,

        Date createdAt


) {}
