package com.projectScope.projectScope.controller;

import com.projectScope.projectScope.dto.ProjectsResDto;
import com.projectScope.projectScope.dto.ProjectsSearchDto;
import com.projectScope.projectScope.model.Projects;
import com.projectScope.projectScope.security.CurrentUser;
import com.projectScope.projectScope.service.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
//@RequestMapping("/projects")
public class ProjectsController {

    private final ProjectsService projectsService;
    private final ModelMapper modelMapper;


    @PostMapping("/projects")
    public ResponseEntity<String> save(@RequestBody Projects projects,
                                       @AuthenticationPrincipal CurrentUser currentUser) {
        projects.setUser(currentUser.getUser());
        projectsService.save(projects);
        return ResponseEntity.ok("Ok");
    }


    @DeleteMapping("/projects/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        projectsService.deleteById(id);
        return ResponseEntity.ok("Ok");
    }

    @PostMapping("/userLeader/projects")
    public ResponseEntity<List<ProjectsResDto>> findAllByUserId(
            @RequestBody(required = false) ProjectsSearchDto searchDto,
            @AuthenticationPrincipal CurrentUser currentUser) {
        List<Projects> projects = projectsService.projectsSearch(currentUser.getUser().getId(), searchDto);
        List<ProjectsResDto> projectsResDto = new ArrayList<>();
        projects.forEach(p -> projectsResDto.add(modelMapper.map(p, ProjectsResDto.class)));
        return ResponseEntity.ok(projectsResDto);
    }
}
