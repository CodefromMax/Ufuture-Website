package com.example.cms.model.repository;

import com.example.cms.model.entity.All_universities;
import com.example.cms.model.entity.CourseMark;
import com.example.cms.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface All_universitiesRepository extends JpaRepository<All_universities, String> {
    /* Is here String okay to use??

     */
    @Query(value = "select * from All_universities u " +
            "where lower(u.uniName) like lower(concat('%', :searchuniName, '%')) " , nativeQuery = true)
    List<All_universities> searchUni(@Param("searchuniName") String searchuniName);
}
