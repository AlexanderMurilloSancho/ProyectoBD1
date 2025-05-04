package com.example.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "identifications")
public class Identification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identification_id")
    private Integer identificationId;

    @ManyToOne
    @JoinColumn(name = "observation_id", nullable = false)
    private Observation observation;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "taxon_id", nullable = false)
    private Taxon taxon;

    @Column(name = "identification_level", nullable = false, length = 100)
    private String identificationLevel;

    @Column(name = "identification_date", nullable = false)
    private Date identificationDate;

    public Integer getIdentificationId() {
        return identificationId;
    }

    public void setIdentificationId(Integer identificationId) {
        this.identificationId = identificationId;
    }

    public Observation getObservation() {
        return observation;
    }

    public void setObservation(Observation observation) {
        this.observation = observation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Taxon getTaxon() {
        return taxon;
    }

    public void setTaxon(Taxon taxon) {
        this.taxon = taxon;
    }

    public String getIdentificationLevel() {
        return identificationLevel;
    }

    public void setIdentificationLevel(String identificationLevel) {
        this.identificationLevel = identificationLevel;
    }

    public Date getIdentificationDate() {
        return identificationDate;
    }

    public void setIdentificationDate(Date identificationDate) {
        this.identificationDate = identificationDate;
    }
}