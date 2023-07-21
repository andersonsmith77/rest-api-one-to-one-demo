package com.example.mapping.restapionetoonedemo.repositories;

import com.example.mapping.restapionetoonedemo.models.TutorialDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialDetailsRepository extends JpaRepository<TutorialDetails, Long> {
    @Transactional
    void deleteById(Long id);
}