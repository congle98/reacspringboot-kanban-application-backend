package com.app.backend.services;

import com.app.backend.domain.ProjectEntity;
import com.app.backend.exceptions.ProjectIdException;
import com.app.backend.exceptions.ProjectIdExceptionResponse;
import com.app.backend.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectEntity saveOrUpdateProject(ProjectEntity project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }
        catch (Exception ex){
         throw new ProjectIdException("Project Id : "+project.getProjectIdentifier().toUpperCase()+" already exists");
        }
    }

    public ProjectEntity findByIdentifier(String id){
        ProjectEntity project = projectRepository.findByProjectIdentifier(id);
        if(project !=null){
            return  project;
        }
        throw new ProjectIdException("ProjectId : "+id.toUpperCase()+ " does not exits");

    }


    public Iterable<ProjectEntity> findAll(){
        return projectRepository.findAll();
    }

    public void DeleteProjectByIdentifier(String id){
        ProjectEntity project = projectRepository.findByProjectIdentifier(id);
        if(project==null){
            throw new ProjectIdException("Can Project with Id: "+id+" , This project does not exist");
        }
        projectRepository.deleteById(project.getId());
    }
}
