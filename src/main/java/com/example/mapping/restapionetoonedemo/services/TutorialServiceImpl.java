package com.example.mapping.restapionetoonedemo.services;

import com.example.mapping.restapionetoonedemo.exceptions.ResourceNotFoundException;
import com.example.mapping.restapionetoonedemo.models.Tutorial;
import com.example.mapping.restapionetoonedemo.repositories.TutorialDetailsRepository;
import com.example.mapping.restapionetoonedemo.repositories.TutorialRepository;
import com.example.mapping.restapionetoonedemo.services.interfaces.TutorialService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialServiceImpl implements TutorialService {

    private TutorialRepository tutorialRepository;
    private TutorialDetailsRepository tutorialDetailsRepository;

    public TutorialServiceImpl(TutorialRepository tutorialRepository, TutorialDetailsRepository tutorialDetailsRepository) {
        this.tutorialRepository = tutorialRepository;
        this.tutorialDetailsRepository = tutorialDetailsRepository;
    }

    @Override
    public Tutorial saveTutorial(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    @Override
    public List<Tutorial> getAllTutorials() {
        return tutorialRepository.findAll();
    }

    @Override
    public Tutorial getTutorialById(Long id) {
        return tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutorial", "Id", id));
    }

    @Override
    public List<Tutorial> getTutorialsByIsPublished(boolean isPublished) {
        return tutorialRepository.findByIsPublished(isPublished);
    }

    @Override
    public List<Tutorial> getTutorialsByTitleContaining(String title) {
        return tutorialRepository.findByTitleContaining(title);
    }

    @Override
    public Tutorial updateTutorial(Tutorial tutorial, Long id) {
        Tutorial existingTutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutorial", "Id", id));

        existingTutorial.setTitle(tutorial.getTitle());
        existingTutorial.setDescription(tutorial.getDescription());
        existingTutorial.setPublished(tutorial.isPublished());

        return tutorialRepository.save(existingTutorial);
    }

    @Override
    public void deleteTutorial(Long id) {
        if (tutorialDetailsRepository.existsById(id))
            tutorialDetailsRepository.deleteById(id);

        tutorialRepository.deleteById(id);
    }

    @Override
    public void deleteAllTutorials() {
        tutorialRepository.deleteAll();
    }
}