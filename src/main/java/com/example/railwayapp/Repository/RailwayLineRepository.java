package com.example.railwayapp.Repository;

import com.example.railwayapp.Model.Entity.RailwayLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RailwayLineRepository extends JpaRepository<RailwayLine, Long> {
}
