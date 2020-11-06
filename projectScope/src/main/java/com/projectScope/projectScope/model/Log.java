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
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue()
    private long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Projects projects;
    private double hours;
    private LocalDateTime date;


}
