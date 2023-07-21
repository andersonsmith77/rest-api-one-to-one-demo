package com.example.mapping.restapionetoonedemo.services.interfaces;

import com.example.mapping.restapionetoonedemo.models.TutorialDetails;

public interface TutorialDetailsService {
    TutorialDetails saveTutorialDetails(TutorialDetails tutorialDetails, Long tutorialId);
    TutorialDetails getTutorialDetailsById(Long id);
    TutorialDetails updateTutorialDetails(TutorialDetails tutorialDetails, Long id);
    void deleteTutorialDetails(Long id);
}