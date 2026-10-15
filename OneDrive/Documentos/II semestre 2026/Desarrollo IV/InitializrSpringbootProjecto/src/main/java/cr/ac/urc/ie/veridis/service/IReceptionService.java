package cr.ac.urc.ie.veridis.service;

import cr.ac.urc.ie.veridis.domain.Reception;
import java.util.List;

public interface IReceptionService {

    void save(Reception r);

    List<Reception> getAll();

    void delete(int pId);

    Reception getById(int pId);

    void update(Reception r);

   // public List<Reception> FindReceptionByName(String search);

}
