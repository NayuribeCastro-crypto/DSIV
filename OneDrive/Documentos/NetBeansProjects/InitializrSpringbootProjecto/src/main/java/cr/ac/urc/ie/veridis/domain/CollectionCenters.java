package cr.ac.urc.ie.veridis.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    public CollectionCenters(){
    }

    public CollectionCenters(int id, String name, String phone, String address, String personInCharge, String observation) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.personInCharge = personInCharge;
        this.observation = observation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public String getObservation() {
        return observation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
    
}
