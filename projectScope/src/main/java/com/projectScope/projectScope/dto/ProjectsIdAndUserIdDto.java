package com.projectScope.projectScope.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectsIdAndUserIdDto {
    private int projectsId;
    private int userId;
}
