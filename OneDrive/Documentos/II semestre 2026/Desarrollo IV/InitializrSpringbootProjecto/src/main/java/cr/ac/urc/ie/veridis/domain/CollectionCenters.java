package cr.ac.urc.ie.veridis.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "collection_Centers")
public class CollectionCenters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(name = "personInCharge")
    private String personInCharge;

    @Column(nullable = false)
    private String observation;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "collectionCenters", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Reception> receptions;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "collectionCenters",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<RecyclingInventory> recyclingInventorys;

    public CollectionCenters() {
    }

    public CollectionCenters(int id, String name, String phone, String address, String personInCharge, String observation, List<Reception> receptions, List<RecyclingInventory> recyclingInventorys) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.personInCharge = personInCharge;
        this.observation = observation;
        this.receptions = receptions;
        this.recyclingInventorys = recyclingInventorys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public List<Reception> getReceptions() {
        return receptions;
    }

    public void setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
    }

    public List<RecyclingInventory> getRecyclingInventorys() {
        return recyclingInventorys;
    }

    public void setRecyclingInventorys(List<RecyclingInventory> recyclingInventorys) {
        this.recyclingInventorys = recyclingInventorys;
    }

    
}
