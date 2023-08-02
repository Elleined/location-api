package com.elleined.locationapi.repository;

import com.elleined.locationapi.model.location.Baranggay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaranggayRepository extends JpaRepository<Baranggay, Integer> {
}