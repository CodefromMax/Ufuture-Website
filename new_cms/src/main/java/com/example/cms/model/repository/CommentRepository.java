package com.example.cms.model.repository;

import com.example.cms.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository {
    @Query(value = "select count(*) from Comment c " +
            "where c.discussionId = :discussionID ", nativeQuery = true)
    Long findNumComment(@Param("discussionID") Long discussionId);
}
