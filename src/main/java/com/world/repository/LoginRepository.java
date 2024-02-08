package com.world.repository;


import com.world.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

    @Query(value = "select * FROM login where username = :username", nativeQuery = true)
    Optional<Login> findByUserName (@Param("username") String username);

    @Query(value = "select count(*) FROM login", nativeQuery = true)
    int countRecord();

}
