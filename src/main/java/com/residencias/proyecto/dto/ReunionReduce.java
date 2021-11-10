package com.residencias.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReunionReduce {

    private String titulo;
    private int numeroParticipantes;
    private String idDepuracion;

}
