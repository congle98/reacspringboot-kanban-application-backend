package com.app.backend.repositories;

import com.app.backend.domain.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {

    ProjectEntity findByProjectIdentifier(String id);



    Optional<ProjectEntity> deleteByProjectIdentifier(String id);
}
