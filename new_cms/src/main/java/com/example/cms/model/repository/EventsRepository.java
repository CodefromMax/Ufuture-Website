package com.example.cms.model.repository;

import com.example.cms.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventsRepository extends JpaRepository<Events, Long>  {
    @Query(value = "select * from Events", nativeQuery = true)
    List<Events> showAllEvents();

    @Query(value = "select * from Events e " +
            "where e.university = :UniId", nativeQuery = true)
    List<Events> showAllEventsForUniversity(@Param("UniId") String uniId);

    @Query(value = "select count(eventCode) from Events e " +
            "where e.university = :UniId", nativeQuery = true)
    Long CountAllEventsForUniversity(@Param("UniId") String uniId);


}
