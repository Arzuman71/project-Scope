package com.projectScope.projectScope.repository;

import com.projectScope.projectScope.model.UserProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface UserProjectsRepo extends JpaRepository<UserProjects, Long> {

    List<UserProjects> findAllByUserId(long id);

    @Query("SELECT p FROM UserProjects p" +
            " WHERE p.user.id = ?1 AND p.projects.name like %?2% AND p.projects.date >= ?3 AND p.projects.date <= ?4")
    List<UserProjects> userProjectsSearch(long userId, String name, LocalDateTime date1, LocalDateTime date2);

}
