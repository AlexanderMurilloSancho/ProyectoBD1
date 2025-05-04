package com.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "taxons")
public class Taxon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taxon_id")
    private Integer taxonId;

    @Column(name = "domain_name", length = 100)
    private String domainName;

    @Column(name = "kingdom_name", length = 100)
    private String kingdomName;

    @Column(name = "phylum_name", length = 100)
    private String phylumName;

    @Column(name = "class_name", length = 100)
    private String className;

    @Column(name = "order_name", length = 100)
    private String orderName;

    @Column(name = "family_name", length = 100)
    private String familyName;

    @Column(name = "genus_name", length = 100)
    private String genusName;

    @Column(name = "species_name", length = 100)
    private String speciesName;

    @Column(name = "common_name", length = 100)
    private String commonName;

    public Integer getTaxonId() {
        return taxonId;
    }

    public void setTaxonId(Integer taxonId) {
        this.taxonId = taxonId;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getKingdomName() {
        return kingdomName;
    }

    public void setKingdomName(String kingdomName) {
        this.kingdomName = kingdomName;
    }

    public String getPhylumName() {
        return phylumName;
    }

    public void setPhylumName(String phylumName) {
        this.phylumName = phylumName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGenusName() {
        return genusName;
    }

    public void setGenusName(String genusName) {
        this.genusName = genusName;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }
}