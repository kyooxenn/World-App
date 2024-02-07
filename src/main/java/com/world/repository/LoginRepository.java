package com.world.repository;


import com.world.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Integer> {

    @Query(value = "select * FROM login where username = :username", nativeQuery = true)
    Optional<Login> findByUserName (@Param("username") String username);

}
