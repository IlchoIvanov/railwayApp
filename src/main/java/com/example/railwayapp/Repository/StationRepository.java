package com.example.railwayapp.Repository;

import com.example.railwayapp.Model.Entity.Enum.StationType;
import com.example.railwayapp.Model.Entity.RailwayLine;
import com.example.railwayapp.Model.Entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    List<Station> findAllByStationType(StationType stationType);
    List<Station> findAllByRailwayLinesContains(RailwayLine railwayLine);
    Station findByName(String name);
    @Query(value = "SELECT s.name  from  Station s where s.stationType = :#{#stationType}")
    List<String> findAllStationNames(@Param("stationType") StationType stationType);
}
