package com.example.mapping.restapionetoonedemo.controllers;

import com.example.mapping.restapionetoonedemo.models.Tutorial;
import com.example.mapping.restapionetoonedemo.services.interfaces.TutorialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {

    private TutorialService tutorialService;

    public TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }

    @PostMapping
    public ResponseEntity<Tutorial> saveTutorial(@RequestBody Tutorial tutorial) {
        return new ResponseEntity<>(tutorialService.saveTutorial(tutorial), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(tutorialService.getTutorialById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Tutorial>> getTutorials(@RequestParam(required = false) String title) {
        List<Tutorial> tutorials = new ArrayList<>();

        if (title == null)
            tutorials.addAll(tutorialService.getAllTutorials());
        else
            tutorials.addAll(tutorialService.getTutorialsByTitleContaining(title));

        if (tutorials.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @GetMapping("/published")
    public ResponseEntity<List<Tutorial>> getTutorialsByIsPublished() {
        List<Tutorial> publishedTutorials = tutorialService.getTutorialsByIsPublished(true);

        if (publishedTutorials.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(publishedTutorials, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") Long id, @RequestBody Tutorial tutorial) {
        return new ResponseEntity<>(tutorialService.updateTutorial(tutorial, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Long id) {
        tutorialService.deleteTutorial(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllTutorials() {
        tutorialService.deleteAllTutorials();

        return new ResponseEntity<>("Tutorials deleted", HttpStatus.OK);
    }
}