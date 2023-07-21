package com.example.mapping.restapionetoonedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tutorial_details")
@Getter
@Setter
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TutorialDetails {
    @Id
    private Long id;
    @Column(name = "created_on")
    private Date createdOn;
    @Column(name = "created_by")
    private String createdBy;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "tutorial_id")
    private Tutorial tutorial;

    public TutorialDetails(String createdBy) {
        this.createdOn = new Date();
        this.createdBy = createdBy;
    }
}