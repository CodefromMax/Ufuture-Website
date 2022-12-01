package com.example.cms.model.repository;

import com.example.cms.model.entity.StudentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentUserRepository extends JpaRepository<StudentUser, String> {

    @Query(value = "select * from StudentUsers s " +
            "where lower(s.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(s.lastName) like lower(concat('%', :searchTerm, '%'))", nativeQuery = true)
    List<StudentUser> search(@Param("searchTerm") String searchTerm);


    @Query(value = "select * from StudentUsers where " +
            "id IN (SELECT s.id FROM StudentUserS s INNER JOIN MARKS m ON s.id = m.StudentUserID " +
            "group by s.id HAVING AVG(Mark) >= 90)", nativeQuery = true)
    List<StudentUser> findTopStudentUsers();
}
