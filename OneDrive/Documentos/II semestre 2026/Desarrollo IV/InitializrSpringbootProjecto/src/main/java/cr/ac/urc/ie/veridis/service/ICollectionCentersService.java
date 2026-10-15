package cr.ac.urc.ie.veridis.service;

import cr.ac.urc.ie.veridis.domain.CollectionCenters;
import java.util.List;

public interface ICollectionCentersService {

    void save(CollectionCenters cC);
    List<CollectionCenters> getAll();
    void delete(int pId);
    CollectionCenters getById(int pId);
    void update(CollectionCenters cC);
    
    public List<CollectionCenters> FindProductByName(String search);

}
