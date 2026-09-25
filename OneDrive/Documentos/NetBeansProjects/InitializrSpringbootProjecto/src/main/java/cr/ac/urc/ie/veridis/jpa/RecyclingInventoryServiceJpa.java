package cr.ac.urc.ie.veridis.jpa;

import cr.ac.urc.ie.veridis.domain.RecyclingInventory;
import cr.ac.urc.ie.veridis.repository.IRecyclingInventoryRepository;
import cr.ac.urc.ie.veridis.service.IRecyclingInventoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecyclingInventoryServiceJpa implements IRecyclingInventoryService {

    @Autowired
    private IRecyclingInventoryRepository repository;

    @Override
    public void save(RecyclingInventory r) {
        repository.createSP(
                r.getWeight(),
                r.getEntryDate(),
                r.getLocation(),
                r.getState(),
                r.getExitDate(),
                r.getQuantity(),
                r.getStorageType(),
                r.getObservations()
        );
    }

    @Override
    public void update(RecyclingInventory r) {
        repository.updateSP(
                r.getId(),
                r.getWeight(),
                r.getEntryDate(),
                r.getLocation(),
                r.getState(),
                r.getExitDate(),
                r.getQuantity(),
                r.getStorageType(),
                r.getObservations()
        );
    }

    @Override
    public List<RecyclingInventory> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(int pId) {
        repository.deleteById(pId);
    }

    @Override
    public RecyclingInventory getById(int pId) {
        return repository.findById(pId).orElse(null);
    }

}
