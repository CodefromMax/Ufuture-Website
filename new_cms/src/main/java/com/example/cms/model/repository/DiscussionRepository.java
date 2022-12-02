package com.example.cms.model.repository;

import com.example.cms.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long>  {
    @Query(value = "select * from Discussion", nativeQuery = true)
    List<Discussion> showAllDiscussions();

    @Query(value = "select * from Discussion " +
            "LEFT JOIN universities ON Discussion.university = universities.universityId " +
            "where universities.uniName = :searchTerm ", nativeQuery = true)
    List<Discussion> showDiscussionForUniversity(@Param("searchTerm") String searchTerm);

}
