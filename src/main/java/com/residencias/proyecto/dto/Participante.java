package com.residencias.proyecto.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "participante")
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombreCompleto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone = "GMT+8")
    private Date horaUnion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone = "GMT+8")
    private Date horaSalida;
    private String duracion;
    private String email;
    private String rol;

    private boolean asistencia;


}
