package com.recruitDemo.recruitDemo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record SearchJobActivityDTO(
        String jobTitle,

        String location,

        String companyName,

        String description,

        String salary,

        List<String> jobType,

        List<String> remote,

        LocalDate createdAt
) {
}
