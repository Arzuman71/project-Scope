package com.projectScope.projectScope.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "projects")
public class Projects {
    @Id
    @GeneratedValue()
    private long id;
    private String name;
    private LocalDateTime date;
    private LocalDateTime deadline;
    private double hours;
    @ManyToOne
    private User user;
}
