package cr.ac.urc.ie.veridis.jpa;

import cr.ac.urc.ie.veridis.domain.CollectionCenters;
import cr.ac.urc.ie.veridis.repository.ICollectionCentersRepository;
import cr.ac.urc.ie.veridis.service.ICollectionCentersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CollectionCentersServiceJPA implements ICollectionCentersService {

    
    @Autowired
    private ICollectionCentersRepository repo;
    
    @Override
    public void save(CollectionCenters cC) {
       repo.createSP(
               cC.getName(),
               cC.getPhone(),
               cC.getAddress(),
               cC.getPersonInCharge(),
               cC.getObservation()
       );
        
    }

     @Override
    public void update(CollectionCenters cC) {
       repo.updateSP(
               cC.getId(),
               cC.getName(),
               cC.getPhone(),
               cC.getAddress(),
               cC.getPersonInCharge(),
               cC.getObservation()
       );
        
    }
    
    @Override
    public List<CollectionCenters> getAll() {
        return repo.getAllSP();
    }

    @Override
    public void delete(int pId) {
        repo.deleteSP(pId);
    }

    
    @Override
    public CollectionCenters getById(int pId) {
        return repo.getByIdSP(pId);
    }


    @Override
    public List<CollectionCenters> FindProductByName(String search) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}
