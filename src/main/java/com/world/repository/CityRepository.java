package com.world.repository;

import com.world.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {


    @Query(value = "select * FROM city where ID = :id", nativeQuery = true)
    List<City> allProducts (@Param("id") int id);




}
