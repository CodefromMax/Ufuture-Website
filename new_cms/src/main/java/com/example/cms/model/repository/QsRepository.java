package com.example.cms.model.repository;
import com.example.cms.model.entity.All_universities;
import com.example.cms.model.entity.CourseMark;
import com.example.cms.model.entity.Qs_rankings;
import com.example.cms.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QsRepository extends JpaRepository<Qs_rankings, String> {

    @Query(value = "select * from QSRanking q " +
            "where q.QS_ranking_id <= :Upperbound ", nativeQuery = true)
    List<Qs_rankings> findTillUpperBound(@Param("Upperbound") Long Upperbound);

    @Query(value = "select * from QSRanking q " +
            "where q.QS_ranking_id >= :Lowerbound ", nativeQuery = true)
    List<Qs_rankings> findTillLowerBound(@Param("Lowerbound") Long Lowerbound);

    @Query(value = "select * from QSRanking q " +
            "where q.QS_ranking_id >= :LB AND q.QS_ranking_id <= :UB ", nativeQuery = true)
    List<Qs_rankings> findSpecificRankings(@Param("LB") Long LB, @Param("UB") Long UB);

    @Query(value = "select * from QSRanking q " +
            "where lower(q.Institution_Name) like lower(concat('%', :qsName, '%')) " , nativeQuery = true)
    List<Qs_rankings> searchQsName(@Param("qsName") String qsName);

}
