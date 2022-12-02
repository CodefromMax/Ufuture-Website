package com.example.cms.model.repository;

import com.example.cms.model.entity.AllUsers;
import com.example.cms.model.entity.StudentUser;
import com.example.cms.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllUsersRepository extends JpaRepository<AllUsers, String>{
}
