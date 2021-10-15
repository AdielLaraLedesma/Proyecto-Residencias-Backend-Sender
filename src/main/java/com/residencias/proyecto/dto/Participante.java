package com.residencias.proyecto.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Participante {

    private String nombreCompleto;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone = "GMT+8")
    private Date horaUnion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone = "GMT+8")
    private Date horaSalida;
    private String duracion;
    private String email;
    private String rol;


}
