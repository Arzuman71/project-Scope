package com.projectScope.projectScope.service;

import com.projectScope.projectScope.dto.ProjectsSearchDto;
import com.projectScope.projectScope.model.Projects;
import com.projectScope.projectScope.model.User;
import com.projectScope.projectScope.model.UserProjects;
import com.projectScope.projectScope.repository.UserProjectsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProjectsService {

    private final UserProjectsRepo userProjectsRepo;


    public void save(User user, Projects projects) {
        UserProjects userProjects = UserProjects.builder()
                .user(user)
                .projects(projects)
                .build();
        userProjectsRepo.save(userProjects);
    }

    public List<Projects> findAllByUserId(long userId, ProjectsSearchDto searchDto) {
        if (searchDto != null) {
            if (searchDto.getName() == null || searchDto.getName().equals("")) {
                searchDto.setName("_");
            } else if (searchDto.getDateFrom() == null) {
                searchDto.setDateFrom(LocalDateTime.parse("1990-01-01T00:00"));
            } else if (searchDto.getDateTo() == null) {
                searchDto.setDateTo(LocalDateTime.parse("2050-01-01T00:00"));
            }
            List<UserProjects> userProjects = userProjectsRepo.userProjectsSearch(userId, searchDto.getName(),
                    searchDto.getDateFrom(), searchDto.getDateTo());
            return getProjectsOfUserProjects(userProjects);
        }
        return getProjectsOfUserProjects(userProjectsRepo.findAllByUserId(userId));
    }

    List<Projects> getProjectsOfUserProjects(List<UserProjects> userProjects) {
        List<Projects> projects = new ArrayList<>();
        if (userProjects != null) {
            userProjects.forEach(p -> projects.add(p.getProjects()));
        }
        return projects;
    }
}
