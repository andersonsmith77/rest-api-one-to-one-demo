package com.example.mapping.restapionetoonedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mapping.restapionetoonedemo.models.Tutorial;

import java.util.List;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByIsPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);
}