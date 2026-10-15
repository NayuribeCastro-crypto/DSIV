package cr.ac.urc.ie.veridis.repository;

import org.springframework.data.repository.query.Param;
import cr.ac.urc.ie.veridis.domain.RecyclingInventory;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IRecyclingInventoryRepository extends JpaRepository<RecyclingInventory, Integer> {

    @Query(value = "CALL sp_RecyclingInventorys_get_all()", nativeQuery = true)
    List<RecyclingInventory> getAllSP();

    @Modifying
    @Transactional
    @Query(value = """
          CALL sp_RecyclingInventorys_create(
                  :weight,
                  :entryDate,
                  :location,
                  :state,
                  :exitDate,
                  :quantity,
                  :storageType,
                  :observations,
                  :collectionCentersId
                  )
                  """, nativeQuery = true)

    void createSP(
            @Param("weight") double weight,
            @Param("entryDate") LocalDate entryDate,
            @Param("location") String location,
            @Param("state") String state,
            @Param("exitDate") LocalDate exitDate,
            @Param("quantity") int quantity,
            @Param("storageType") String storageType,
            @Param("observations") String observations,
            @Param("collectionCentersId") int collectionCentersId
    );

    @Modifying
    @Transactional
    @Query(value = """
          CALL sp_RecyclingInventorys_update(
                  :id,
                  :weight,
                  :entryDate,
                  :location,
                  :state,
                  :exitDate,
                  :quantity,
                  :storageType,
                  :observations,
                  :collectionCentersId 
                  )
                  """, nativeQuery = true)

    void updateSP(
            @Param("id") int id,
            @Param("weight") double weight,
            @Param("entryDate") LocalDate entryDate,
            @Param("location") String location,
            @Param("state") String state,
            @Param("exitDate") LocalDate exitDate,
            @Param("quantity") int quantity,
            @Param("storageType") String storageType,
            @Param("observations") String observations,
            @Param("collectionCentersId") int collectionCentersId
    );

    @Modifying
    @Transactional
    @Query(value = """
        CALL sp_RecyclingInventory_delete(
                   :id)
        """, nativeQuery = true)

    void deleteSP(
            @Param("id") int id);

    @Query(value = """
        CALL sp_RecyclingInventory_get_by_id(
                   :id)
        """, nativeQuery = true)
    RecyclingInventory getByIdSP(
            @Param("id") int id);

    // @Query("Select rI from RecyclingInventory rI where lower(rI.name) like lower(concat('%',:search,'%'))")
    // public List<RecyclingInventory> FindRecyclingInventoryByName(String search);
}
