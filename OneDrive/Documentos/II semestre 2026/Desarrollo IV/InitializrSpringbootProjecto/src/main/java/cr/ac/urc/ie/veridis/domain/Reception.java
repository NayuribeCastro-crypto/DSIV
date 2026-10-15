package cr.ac.urc.ie.veridis.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "reception")
public class Reception {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ReceptionDate")
    private LocalDate ReceptionDate;

    @Column(name = "weight")
    private double weight;

    @Column(nullable = false)
    private String observations;

    @Column(name = "origin")
    private String origin;

    @Column(nullable = false)
    private String materialCondition;

    @Column(nullable = false)
    private String classification;

    @Column(name = "receptionStatus")
    private String receptionStatus;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collectionCenters_id")
    private CollectionCenters collectionCenters;

    public Reception() {
    }

    public Reception(int id, LocalDate ReceptionDate, double weight, String observations, String origin, String materialCondition, String classification, String receptionStatus,
            CollectionCenters collectionCenters) {

        this.id = id;
        this.ReceptionDate = ReceptionDate;
        this.weight = weight;
        this.observations = observations;
        this.origin = origin;
        this.materialCondition = materialCondition;
        this.classification = classification;
        this.receptionStatus = receptionStatus;
        this.collectionCenters = collectionCenters;

    }

    public int getId() {
        return id;
    }

    public LocalDate getReceptionDate() {
        return ReceptionDate;
    }

    public double getWeight() {
        return weight;
    }

    public String getObservations() {
        return observations;
    }

    public String getOrigin() {
        return origin;
    }

    public String getMaterialCondition() {
        return materialCondition;
    }

    public String getClassification() {
        return classification;
    }

    public String getReceptionStatus() {
        return receptionStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReceptionDate(LocalDate ReceptionDate) {
        this.ReceptionDate = ReceptionDate;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setMaterialCondition(String materialCondition) {
        this.materialCondition = materialCondition;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setReceptionStatus(String receptionStatus) {
        this.receptionStatus = receptionStatus;
    }

    public CollectionCenters getCollectionCenters() {
        return collectionCenters;
    }

    public void setCollectionCenters(CollectionCenters collectionCenters) {
        this.collectionCenters = collectionCenters;
    }
    
    

}
