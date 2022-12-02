package com.example.cms.model.repository;

import com.example.cms.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, CommentKey>  {
    @Query(value = "select * from Comment c " +
            "where c.discussionId = :DiscussionId", nativeQuery = true)
    List<Comment> showCommentForDiscussion(@Param("DiscussionId") Long disId);

    @Query(value = "select count(commentId) from Comment c " +
            "where c.discussionId = :DiscussionId", nativeQuery = true)
    Long countForComment(@Param("DiscussionId") Long DisId);

}
