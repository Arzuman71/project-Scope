package com.projectScope.projectScope.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_projects")
public class UserProjects {
    @Id
    @GeneratedValue()
    private long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Projects projects;

}
