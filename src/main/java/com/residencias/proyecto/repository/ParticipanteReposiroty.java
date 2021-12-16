package com.residencias.proyecto.repository;

import com.residencias.proyecto.dto.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteReposiroty extends JpaRepository<Participante, Integer> {
}
