package com.residencias.proyecto.repository;

import com.residencias.proyecto.dto.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ReunionRepository extends JpaRepository<Reunion, Integer> {

}