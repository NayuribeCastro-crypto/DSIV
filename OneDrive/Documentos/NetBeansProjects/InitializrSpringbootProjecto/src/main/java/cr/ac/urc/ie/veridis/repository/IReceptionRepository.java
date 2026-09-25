package cr.ac.urc.ie.veridis.repository;

import cr.ac.urc.ie.veridis.domain.Reception;
import java.util.List;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IReceptionRepository extends JpaRepository<Reception, Integer> {

    @Query(value = "CALL sp_Reception_get_all()", nativeQuery = true)
    List<Reception> getAllSP();

    @Modifying
    @Transactional
    @Query(value = """
          CALL sp_Reception_create(
                  :ReceptionDate,
                  :weight,
                  :observations,
                  :origin,
                  :materialCondition,
                  :classification,
                  :receptionStatus
                  )
                 
                  """, nativeQuery = true)

    void createSP(
            @Param("ReceptionDate") LocalDate ReceptionDate,
            @Param("weight") double weight,
            @Param("observations") String observations,
            @Param("origin") String origin,
            @Param("materialCondition") String materialCondition,
            @Param("classification") String classification,
            @Param("receptionStatus") String receptionStatus
    );

    @Modifying
    @Transactional
    @Query(value = """
          CALL sp_Reception_update(
                          :id,
                  :ReceptionDate,
                  :weight,
                  :observations,
                  :origin,
                  :materialCondition,
                  :classification,
                  :receptionStatus
                  )
                 
                  """, nativeQuery = true)

    void updateSP(
            @Param("id") int id,
            @Param("ReceptionDate") LocalDate ReceptionDate,
            @Param("weight") double weight,
            @Param("observations") String observations,
            @Param("origin") String origin,
            @Param("materialCondition") String materialCondition,
            @Param("classification") String classification,
            @Param("receptionStatus") String receptionStatus
    );

    //@Query("Select r from Reception r where lower(r.name) like lower(concat('%',:search,'%'))")
   // public List<Reception> FindReceptionByName(String search);

}
