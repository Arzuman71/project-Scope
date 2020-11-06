package com.projectScope.projectScope.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectsResDto {
    private long id;
    private String name;
    private LocalDateTime date;
    private LocalDateTime deadline;
    private double hours;
}
