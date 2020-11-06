package com.projectScope.projectScope.service;

import com.projectScope.projectScope.dto.ProjectsSearchDto;
import com.projectScope.projectScope.model.Projects;
import com.projectScope.projectScope.repository.ProjectsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectsService {

    private final ProjectsRepo projectsRepo;

    public void save(Projects projects) {
        projectsRepo.save(projects);

    }

    public void removeAllByIdAndUserId(int[] projectsId, long userId) {
        boolean bool;
        int index = 0;
        do {
            bool = projectsRepo.removeByIdAndUserId(projectsId[index], userId);
            index++;
        } while (bool);

    }

    public Projects getOne(long projectsId) {
        return projectsRepo.getOne(projectsId);
    }

    public List<Projects> findAllByUserId(long userId) {
        return projectsRepo.findAllByUserId(userId);
    }

    public List<Projects> projectsSearch(long userId, ProjectsSearchDto searchDto) {
        if (searchDto != null) {
            if (searchDto.getName() == null || searchDto.getName().equals("")) {
                searchDto.setName("_");
            } else if (searchDto.getDateFrom() == null) {
                searchDto.setDateFrom(LocalDateTime.parse("1990-01-01T00:00"));
            } else if (searchDto.getDateTo() == null) {
                searchDto.setDateTo(LocalDateTime.parse("2050-01-01T00:00"));
            }
            return projectsRepo.projectsSearch(userId, searchDto.getName(),
                    searchDto.getDateFrom(), searchDto.getDateTo());
        }
        return projectsRepo.findAllByUserId(userId);
    }

    public void deleteById(long id) {
        projectsRepo.deleteById(id);
    }

}
