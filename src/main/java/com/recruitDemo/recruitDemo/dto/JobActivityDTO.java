package com.recruitDemo.recruitDemo.dto;

import java.util.Date;

public record JobActivityDTO(
   int id,
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
