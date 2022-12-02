package com.example.cms.model.repository;
import com.example.cms.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimesRepository extends JpaRepository<Times_rankings, String> {
    @Query(value = "select * from TimesRankings t " +
            "where lower(t.University_Name) like lower(concat('%', :timesName, '%')) " , nativeQuery = true)
    List<Times_rankings> searchTimesName(@Param("timesName") String tN);
}
