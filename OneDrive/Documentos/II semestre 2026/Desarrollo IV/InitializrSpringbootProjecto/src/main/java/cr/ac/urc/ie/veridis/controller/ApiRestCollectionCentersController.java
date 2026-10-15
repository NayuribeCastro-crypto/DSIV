package cr.ac.urc.ie.veridis.controller;

import cr.ac.urc.ie.veridis.domain.CollectionCenters;
import cr.ac.urc.ie.veridis.service.ICollectionCentersService;
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
@RequestMapping("api/collectionCenters")
//Ruta de ejecución del React
@CrossOrigin(originPatterns = "http://localhost:*")
public class ApiRestCollectionCentersController {

    @Autowired
    private ICollectionCentersService service;

    @GetMapping("/all")
    public List<CollectionCenters> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        CollectionCenters center = find(id);
        if (center == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(center);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<?> create(@RequestBody CollectionCenters center) {
        if (isBlank(center.getName()) || isBlank(center.getPhone()) || isBlank(center.getAddress())) {
            return ResponseEntity.badRequest()
                    .body("name, phone y address son requeridos");
        }
        if (center.getObservation() == null) {
            center.setObservation(""); // la columna observation es NOT NULL
        }
        try {
            service.save(center);
            return ResponseEntity.status(HttpStatus.CREATED).body(center);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se pudo crear el centro de acopio");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody CollectionCenters data) {
        CollectionCenters center = find(id);
        if (center == null) {
            return ResponseEntity.notFound().build();
        }

        if (!isBlank(data.getName())) {
            center.setName(data.getName());
        }
        if (!isBlank(data.getPhone())) {
            center.setPhone(data.getPhone());
        }
        if (!isBlank(data.getAddress())) {
            center.setAddress(data.getAddress());
        }
        if (data.getPersonInCharge() != null) {
            center.setPersonInCharge(data.getPersonInCharge());
        }
        if (data.getObservation() != null) {
            center.setObservation(data.getObservation());
        }

        try {
            service.update(center);
            return ResponseEntity.ok(center);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se pudo actualizar el centro de acopio");
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
                    .body("No se puede eliminar: el centro tiene recepciones o inventario asociado");
        }
    }

    private CollectionCenters find(int id) {
        try {
            return service.getById(id); // el SP devuelve null si no existe
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}
