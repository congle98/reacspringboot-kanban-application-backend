package com.app.backend.controller;

import com.app.backend.domain.ProjectEntity;
import com.app.backend.services.MapValidationErrorService;
import com.app.backend.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping
    public ResponseEntity<?> createProject(@Valid @RequestBody ProjectEntity project, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap !=null){
            return errorMap;
        }
        return new ResponseEntity<>(projectService.saveOrUpdateProject(project), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable String id){
        return new ResponseEntity<>(projectService.findByIdentifier(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(projectService.findAll(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable String id){
        projectService.DeleteProjectByIdentifier(id);
        return new ResponseEntity<>("Project with id: "+id+" was deleted",HttpStatus.OK);
    }

}
