package com.projectScope.projectScope.repository;

import com.projectScope.projectScope.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectsRepo extends JpaRepository<Projects, Long> {

    boolean removeByIdAndUserId(long projectsId, long userId);


    List<Projects> findAllByUserId(long userId);

    @Query("SELECT p FROM Projects p" +
            " WHERE p.user.id = ?1 AND p.name like %?2% AND p.date >= ?3 AND p.date <= ?4")
    List<Projects> projectsSearch(long userId, String name, LocalDateTime date1, LocalDateTime date2);


}
