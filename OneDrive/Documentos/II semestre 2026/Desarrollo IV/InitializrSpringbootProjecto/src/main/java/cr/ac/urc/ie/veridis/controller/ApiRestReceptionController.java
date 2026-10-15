package cr.ac.urc.ie.veridis.controller;

import cr.ac.urc.ie.veridis.domain.CollectionCenters;
import cr.ac.urc.ie.veridis.domain.Reception;
import cr.ac.urc.ie.veridis.service.ICollectionCentersService;
import cr.ac.urc.ie.veridis.service.IReceptionService;
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
@RequestMapping("api/receptions")
//Ruta de ejecución del React
@CrossOrigin(originPatterns = "http://localhost:5173")

public class ApiRestReceptionController {

    /**
     * Body que envía el React. Se usa porque Reception.collectionCenters tiene
     * @JsonIgnore y no puede llegar dentro del JSON de la entidad.
     */
    public record ReceptionRequest(
            LocalDate receptionDate,
            Double weight,
            String observations,
            String origin,
            String materialCondition,
            String classification,
            String receptionStatus,
            Integer collectionCentersId) {

    }

    @Autowired
    private IReceptionService service;

    @Autowired
    private ICollectionCentersService centersService;

    @GetMapping("/all")
    public List<Reception> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        
        Reception reception = find(id);
        if (reception == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reception);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<?> create(@RequestBody ReceptionRequest req) {
        
        if (req.weight() == null || isBlank(req.materialCondition())
                || isBlank(req.classification()) || req.collectionCentersId() == null) {
            return ResponseEntity.badRequest()
                    .body("weight, materialCondition, classification y collectionCentersId son requeridos");
        }
        CollectionCenters center = findCenter(req.collectionCentersId());
        if (center == null) {
            return ResponseEntity.badRequest().body("El centro de acopio indicado no existe");
        }

        Reception reception = new Reception();
        reception.setReceptionDate(req.receptionDate());
        reception.setWeight(req.weight());
        reception.setObservations(req.observations() == null ? "" : req.observations()); // NOT NULL
        reception.setOrigin(req.origin());
        reception.setMaterialCondition(req.materialCondition());
        reception.setClassification(req.classification());
        reception.setReceptionStatus(req.receptionStatus());
        reception.setCollectionCenters(center);

        try {
            service.save(reception);
            return ResponseEntity.status(HttpStatus.CREATED).body(reception);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se pudo crear la recepción");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody ReceptionRequest req) {
        
        Reception reception = find(id);
        if (reception == null) {
            return ResponseEntity.notFound().build();
        }

        if (req.receptionDate() != null) {
            reception.setReceptionDate(req.receptionDate());
        }
        if (req.weight() != null) {
            reception.setWeight(req.weight());
        }
        if (req.observations() != null) {
            reception.setObservations(req.observations());
        }
        if (req.origin() != null) {
            reception.setOrigin(req.origin());
        }
        if (!isBlank(req.materialCondition())) {
            reception.setMaterialCondition(req.materialCondition());
        }
        if (!isBlank(req.classification())) {
            reception.setClassification(req.classification());
        }
        if (req.receptionStatus() != null) {
            reception.setReceptionStatus(req.receptionStatus());
        }
        if (req.collectionCentersId() != null) {
            CollectionCenters center = findCenter(req.collectionCentersId());
            if (center == null) {
                return ResponseEntity.badRequest().body("El centro de acopio indicado no existe");
            }
            reception.setCollectionCenters(center);
        }
        if (reception.getCollectionCenters() == null) {
            return ResponseEntity.badRequest().body("collectionCentersId es requerido");
        }

        try {
            service.update(reception);
            return ResponseEntity.ok(reception);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se pudo actualizar la recepción");
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
                    .body("No se pudo eliminar la recepción");
        }
    }

    private Reception find(int id) {
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