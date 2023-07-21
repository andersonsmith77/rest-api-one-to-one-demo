package com.example.mapping.restapionetoonedemo.controllers;

import com.example.mapping.restapionetoonedemo.models.TutorialDetails;
import com.example.mapping.restapionetoonedemo.services.interfaces.TutorialDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TutorialDetailsController {

    private TutorialDetailsService tutorialDetailsService;

    public TutorialDetailsController(TutorialDetailsService tutorialDetailsService) {
        this.tutorialDetailsService = tutorialDetailsService;
    }

    @PostMapping("/tutorials/{tutorialId}/details")
    public ResponseEntity<TutorialDetails> saveTutorialDetails(@PathVariable("tutorialId") Long tutorialId, @RequestBody TutorialDetails tutorialDetails) {
        return new ResponseEntity<>(tutorialDetailsService.saveTutorialDetails(tutorialDetails, tutorialId), HttpStatus.CREATED);
    }

    @GetMapping({"/details/{id}", "tutorials/{id}/details"})
    public ResponseEntity<TutorialDetails> getDetailsById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(tutorialDetailsService.getTutorialDetailsById(id), HttpStatus.OK);
    }

    @PutMapping("details/{id}")
    public ResponseEntity<TutorialDetails> updateTutorialDetails(@PathVariable("id") Long id, @RequestBody TutorialDetails tutorialDetails) {
        return new ResponseEntity<>(tutorialDetailsService.updateTutorialDetails(tutorialDetails, id), HttpStatus.OK);
    }

    @DeleteMapping({"/details/{id}", "/tutorials/{id}/details"})
    public ResponseEntity<String> deleteTutorialDetails(@PathVariable("id") Long id) {
        tutorialDetailsService.deleteTutorialDetails(id);

        return new ResponseEntity<>("Details of tutorials deleted", HttpStatus.OK);
    }
}
