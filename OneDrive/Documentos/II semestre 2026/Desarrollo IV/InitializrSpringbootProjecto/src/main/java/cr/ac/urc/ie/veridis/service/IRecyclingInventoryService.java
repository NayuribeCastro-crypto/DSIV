
package cr.ac.urc.ie.veridis.service;

import cr.ac.urc.ie.veridis.domain.RecyclingInventory;
import java.util.List;


public interface IRecyclingInventoryService {
    
     void save (RecyclingInventory rI);
      void update (RecyclingInventory rI);
    List<RecyclingInventory> getAll();
    void delete (int pId);
    RecyclingInventory getById (int pId);

    //public List<RecyclingInventory> FindProductByName(String search);
    
}
