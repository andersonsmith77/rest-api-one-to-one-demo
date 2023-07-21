package com.example.mapping.restapionetoonedemo.services;

import com.example.mapping.restapionetoonedemo.exceptions.ResourceNotFoundException;
import com.example.mapping.restapionetoonedemo.models.Tutorial;
import com.example.mapping.restapionetoonedemo.models.TutorialDetails;
import com.example.mapping.restapionetoonedemo.repositories.TutorialDetailsRepository;
import com.example.mapping.restapionetoonedemo.repositories.TutorialRepository;
import com.example.mapping.restapionetoonedemo.services.interfaces.TutorialDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TutorialDetailServiceImpl implements TutorialDetailsService {

    private TutorialDetailsRepository tutorialDetailsRepository;
    private TutorialRepository tutorialRepository;

    public TutorialDetailServiceImpl(TutorialDetailsRepository tutorialDetailsRepository, TutorialRepository tutorialRepository) {
        this.tutorialDetailsRepository = tutorialDetailsRepository;
        this.tutorialRepository = tutorialRepository;
    }

    @Override
    public TutorialDetails saveTutorialDetails(TutorialDetails tutorialDetails, Long tutorialId) {
        Tutorial tutorial = tutorialRepository.findById(tutorialId)
                .orElseThrow(() -> new ResourceNotFoundException("Tutorial", "Id", tutorialId));

        tutorialDetails.setCreatedOn(new Date());
        tutorialDetails.setTutorial(tutorial);

        return tutorialDetailsRepository.save(tutorialDetails);
    }

    @Override
    public TutorialDetails getTutorialDetailsById(Long id) {
        return tutorialDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TutorialDetails", "Id", id));
    }

    @Override
    public TutorialDetails updateTutorialDetails(TutorialDetails tutorialDetails, Long id) {
        TutorialDetails existingTutorialDetails = tutorialDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TutorialDetails", "Id", id));

        existingTutorialDetails.setCreatedBy(tutorialDetails.getCreatedBy());

        return tutorialDetailsRepository.save(existingTutorialDetails);
    }

    @Override
    public void deleteTutorialDetails(Long id) {
        if (!tutorialRepository.existsById(id))
            throw new ResourceNotFoundException("Tutorial", "Id", id);

        tutorialDetailsRepository.deleteById(id);
    }
}
