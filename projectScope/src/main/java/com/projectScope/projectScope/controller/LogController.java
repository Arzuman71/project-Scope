package com.projectScope.projectScope.controller;

import com.projectScope.projectScope.dto.LogDto;
import com.projectScope.projectScope.model.Log;
import com.projectScope.projectScope.model.Projects;
import com.projectScope.projectScope.security.CurrentUser;
import com.projectScope.projectScope.service.LogService;
import com.projectScope.projectScope.service.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class LogController {
    private final ProjectsService projectsService;
    private final LogService logService;


    @PostMapping("/log")
    public ResponseEntity<String> save(@RequestBody LogDto logDto,
                                       @AuthenticationPrincipal CurrentUser currentUser) {
        Projects projects = projectsService.getOne(logDto.getProjectId());
        projects.setHours(projects.getHours() + logDto.getHours());
        projectsService.save(projects);
        Log log = Log.builder()
                .date(logDto.getDate())
                .projects(projects)
                .user(currentUser.getUser())
                .hours(logDto.getHours())
                .build();
        logService.save(log);
        return ResponseEntity.ok("Ok");
    }

    // http://localhost:8080/log/1,2,3,4,7
    @DeleteMapping("/log/{listId}")
    public ResponseEntity<String> delete(@PathVariable("listId") List<Long> listId) {
        logService.deleteById(listId);
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/logs")
    public ResponseEntity<List<Log>> findAllByUserId(@AuthenticationPrincipal CurrentUser currentUser) {
        List<Log> logs = logService.findAllByUserId(currentUser.getUser().getId());
        return ResponseEntity.ok(logs);
    }
}
