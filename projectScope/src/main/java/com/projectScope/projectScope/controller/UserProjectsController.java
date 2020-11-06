package com.projectScope.projectScope.controller;

import com.projectScope.projectScope.dto.ProjectsIdAndUserIdDto;
import com.projectScope.projectScope.dto.ProjectsSearchDto;
import com.projectScope.projectScope.model.Projects;
import com.projectScope.projectScope.model.User;
import com.projectScope.projectScope.security.CurrentUser;
import com.projectScope.projectScope.service.ProjectsService;
import com.projectScope.projectScope.service.UserProjectsService;
import com.projectScope.projectScope.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class UserProjectsController {

    private final UserProjectsService userProjectsService;
    private final UserService userService;
    private final ProjectsService projectsService;


    @PostMapping("/projects/user/add")
    public ResponseEntity<String> addUserInProjects(@RequestBody ProjectsIdAndUserIdDto dto) {
        User user = userService.getOne(dto.getUserId());
        Projects projects = projectsService.getOne(dto.getProjectsId());
        userProjectsService.save(user, projects);
        return ResponseEntity.ok("Ok");
    }

    @PostMapping("/userMember/projects")
    public ResponseEntity<List<Projects>> findAllByUserId(
            @RequestBody(required = false) ProjectsSearchDto searchDto,
            @AuthenticationPrincipal CurrentUser currentUser) {

        List<Projects> projects = userProjectsService.findAllByUserId(
                currentUser.getUser().getId(),
                searchDto);
        return ResponseEntity.ok(projects);
    }
}
