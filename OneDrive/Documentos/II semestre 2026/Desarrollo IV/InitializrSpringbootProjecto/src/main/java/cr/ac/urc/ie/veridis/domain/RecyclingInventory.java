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
@Table(name="recycling_Inventory")
public class RecyclingInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private double weight;

    @Column(name = "entryDate")
    private LocalDate  entryDate;

    
    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String state;

    @Column(name = "exitDate")
    private LocalDate exitDate;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "storageType")
    private String storageType;

    @Column(nullable = false)
    private String observations;

    
    @JsonIgnore
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "collectionCenters_id")
    
    private CollectionCenters collectionCenters;
    

    public RecyclingInventory() {
    }

    public RecyclingInventory(int id, double weight, LocalDate entryDate, String location, String state, LocalDate exitDate, int quantity, String storageType, String observations,
            CollectionCenters collectionCenters) {
        this.id = id;
        this.weight = weight;
        this.entryDate = entryDate;
        this.location = location;
        this.state = state;
        this.exitDate = exitDate;
        this.quantity = quantity;
        this.storageType = storageType;
        this.observations = observations;
        this.collectionCenters = collectionCenters;
    }

   

    public int getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public String getLocation() {
        return location;
    }

    public String getState() {
        return state;
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStorageType() {
        return storageType;
    }

    public String getObservations() {
        return observations;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public CollectionCenters getCollectionCenters() {
        return collectionCenters;
    }

    public void setCollectionCenters(CollectionCenters collectionCenters) {
        this.collectionCenters = collectionCenters;
    }
   
}
