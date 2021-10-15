package com.residencias.proyecto.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Reunion {

    private Boolean revisionPrevia;
    private int numeroParticipantes;

    private String titulo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone = "GMT+8")
    private Date horaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone = "GMT+8")
    private Date horaFin;

    private String idDepuracion;


    private List<Participante> participantes;



}
