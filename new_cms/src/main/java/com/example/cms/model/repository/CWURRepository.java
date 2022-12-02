package com.example.cms.model.repository;
import com.example.cms.model.entity.All_universities;
import com.example.cms.model.entity.CWUR_rankings;
import com.example.cms.model.entity.Qs_rankings;
import com.example.cms.model.entity.StudentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CWURRepository extends JpaRepository<CWUR_rankings, String> {
    @Query(value = "select * from CWURRankings c " +
            "where lower(c.Institution_Name) like lower(concat('%', :cwurName, '%')) " , nativeQuery = true)
    List<CWUR_rankings> searchCwurName(@Param("cwurName") String cwurName);
}
