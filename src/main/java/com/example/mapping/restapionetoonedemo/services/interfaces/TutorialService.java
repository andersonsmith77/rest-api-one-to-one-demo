package com.example.mapping.restapionetoonedemo.services.interfaces;

import com.example.mapping.restapionetoonedemo.models.Tutorial;

import java.util.List;

public interface TutorialService {
    Tutorial saveTutorial(Tutorial tutorial);
    Tutorial getTutorialById(Long id);
    List<Tutorial> getAllTutorials();
    List<Tutorial> getTutorialsByIsPublished(boolean published);
    List<Tutorial> getTutorialsByTitleContaining(String title);
    Tutorial updateTutorial(Tutorial tutorial, Long id);
    void deleteTutorial(Long id);
    void deleteAllTutorials();
}