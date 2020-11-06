package com.projectScope.projectScope.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogDto {
    @NotNull
    private long projectId;
    @NotNull
    private double hours;
    private LocalDateTime date;
}
