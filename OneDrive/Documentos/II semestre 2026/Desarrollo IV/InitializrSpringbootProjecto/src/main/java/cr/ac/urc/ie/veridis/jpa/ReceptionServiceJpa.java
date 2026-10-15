package cr.ac.urc.ie.veridis.jpa;

import cr.ac.urc.ie.veridis.domain.Reception;
import cr.ac.urc.ie.veridis.repository.IReceptionRepository;
import cr.ac.urc.ie.veridis.service.IReceptionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceptionServiceJpa implements IReceptionService {

    @Autowired
    private IReceptionRepository repository;

    @Override
    public void save(Reception r) {

        repository.createSP(
                r.getReceptionDate(),
                r.getWeight(),
                r.getObservations(),
                r.getOrigin(),
                r.getMaterialCondition(),
                r.getClassification(),
                r.getReceptionStatus(),
                r.getCollectionCenters().getId()
        );
    }

    @Override
    public void update(Reception r) {

        repository.updateSP(
                r.getId(),
                r.getReceptionDate(),
                r.getWeight(),
                r.getObservations(),
                r.getOrigin(),
                r.getMaterialCondition(),
                r.getClassification(),
                r.getReceptionStatus(),
                r.getCollectionCenters().getId()
        );
    }

    @Override
    public List<Reception> getAll() {
        return repository.getAllSP();
    }

    @Override
    public void delete(int pId) {
        repository.deleteSP(pId);
    }

    @Override
    public Reception getById(int pId) {
        return repository.getByIdSP(pId);
    }

    //@Override
    //public List<Reception> FindReceptionByName(String search) {
    // return repository.FindReceptionByName(search);
    // }
}
