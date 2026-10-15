package cr.ac.urc.ie.veridis.repository;

import java.util.List;
import cr.ac.urc.ie.veridis.domain.CollectionCenters;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ICollectionCentersRepository extends JpaRepository<CollectionCenters, Integer> {

    @Query(value = "CALL sp_CollectionCenters_get_all()", nativeQuery = true)
    List<CollectionCenters> getAllSP();

    @Modifying
    @Transactional
    @Query(value = """
          CALL sp_CollectionCenters_create(
                  :name,
                  :phone,
                  :address,
                  :personInCharge,
                  :observation)
                  """, nativeQuery = true)

    void createSP(
            @Param("name") String name,
            @Param("phone") String phone,
            @Param("address") String address,
            @Param("personInCharge") String personInCharge,
            @Param("observation") String observation
    );

    @Modifying
    @Transactional
    @Query(value = """
          CALL sp_CollectionCenters_update(
                  :id,
                  :name,
                  :phone,
                  :address,
                  :personInCharge,
                  :observation)
                  """, nativeQuery = true)

    void updateSP(
            @Param("id") int id,
            @Param("name") String name,
            @Param("phone") String phone,
            @Param("address") String address,
            @Param("personInCharge") String personInCharge,
            @Param("observation") String observation
    );

    @Modifying
    @Transactional
    @Query(value = """
        CALL sp_CollectionCenters_delete(
                   :id)
        """, nativeQuery = true)

    void deleteSP(
            @Param("id") int id);

    
    @Query(value = """
        CALL sp_CollectionCenters_get_by_id(
                   :id)
        """, nativeQuery = true)
    CollectionCenters getByIdSP(
            @Param("id") int id);
    
    
    @Query("Select cC from CollectionCenters cC where lower(cC.name) like lower(concat('%',:search,'%'))")
    public List<CollectionCenters> FindCollectionCentersByName(String search);

}
