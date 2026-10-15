package cr.ac.urc.ie.veridis.controller;

import cr.ac.urc.ie.veridis.domain.CollectionCenters;
import cr.ac.urc.ie.veridis.domain.RecyclingInventory;
import cr.ac.urc.ie.veridis.service.ICollectionCentersService;
import cr.ac.urc.ie.veridis.service.IRecyclingInventoryService;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/recyclingInventories")
//Ruta de ejecución del React
@CrossOrigin(originPatterns = "http://localhost:*")
public class ApiRestRecyclingInventoryController {

    /**
     * Body que envía el React. Se usa porque RecyclingInventory.collectionCenters
     * tiene @JsonIgnore y no puede llegar dentro del JSON de la entidad.
     */
    public record RecyclingInventoryRequest(
            Double weight,
            LocalDate entryDate,
            String location,
            String state,
            LocalDate exitDate,
            Integer quantity,
            String storageType,
            String observations,
            Integer collectionCentersId) {

    }

    @Autowired
    private IRecyclingInventoryService service;

    @Autowired
    private ICollectionCentersService centersService;

    @GetMapping("/all")
    public List<RecyclingInventory> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        RecyclingInventory inventory = find(id);
        if (inventory == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inventory);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<?> create(@RequestBody RecyclingInventoryRequest req) {
        
        if (req.weight() == null || req.quantity() == null || isBlank(req.location())
                || isBlank(req.state()) || req.collectionCentersId() == null) {
            
            return ResponseEntity.badRequest()
                    .body("weight, quantity, location, state y collectionCentersId son requeridos");
        }
        
        CollectionCenters center = findCenter(req.collectionCentersId());
        if (center == null) {
            return ResponseEntity.badRequest().body("El centro de acopio indicado no existe");
        }

        RecyclingInventory inventory = new RecyclingInventory();
        inventory.setWeight(req.weight());
        inventory.setEntryDate(req.entryDate());
        inventory.setLocation(req.location());
        inventory.setState(req.state());
        inventory.setExitDate(req.exitDate());
        inventory.setQuantity(req.quantity());
        inventory.setStorageType(req.storageType());
        inventory.setObservations(req.observations() == null ? "" : req.observations()); // NOT NULL
        inventory.setCollectionCenters(center);

        try {
            service.save(inventory);
            return ResponseEntity.status(HttpStatus.CREATED).body(inventory);
            
        } catch (DataAccessException e) {
            
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se pudo crear el registro de inventario");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody RecyclingInventoryRequest req) {
        
        RecyclingInventory inventory = find(id);
        if (inventory == null) {
            return ResponseEntity.notFound().build();
        }

        if (req.weight() != null) {
            inventory.setWeight(req.weight());
        }
        if (req.entryDate() != null) {
            inventory.setEntryDate(req.entryDate());
        }
        if (!isBlank(req.location())) {
            inventory.setLocation(req.location());
        }
        if (!isBlank(req.state())) {
            inventory.setState(req.state());
        }
        if (req.exitDate() != null) {
            inventory.setExitDate(req.exitDate());
        }
        if (req.quantity() != null) {
            inventory.setQuantity(req.quantity());
        }
        if (req.storageType() != null) {
            inventory.setStorageType(req.storageType());
        }
        if (req.observations() != null) {
            inventory.setObservations(req.observations());
        }
        if (req.collectionCentersId() != null) {
            CollectionCenters center = findCenter(req.collectionCentersId());
            if (center == null) {
                return ResponseEntity.badRequest().body("El centro de acopio indicado no existe");
            }
            inventory.setCollectionCenters(center);
        }
        if (inventory.getCollectionCenters() == null) {
            return ResponseEntity.badRequest().body("collectionCentersId es requerido");
        }

        try {
            service.update(inventory);
            return ResponseEntity.ok(inventory);
            
        } catch (DataAccessException e) {
            
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se pudo actualizar el registro de inventario");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        
        if (find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
            
        } catch (DataAccessException e) {
            
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se pudo eliminar el registro de inventario");
        }
    }

    private RecyclingInventory find(int id) {
        
        try {
            return service.getById(id); // el SP devuelve null si no existe
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private CollectionCenters findCenter(int id) {
        
        try {
            return centersService.getById(id);
            
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}