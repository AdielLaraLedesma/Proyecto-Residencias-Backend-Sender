package com.residencias.proyecto.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "reunion")
public class Reunion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int numeroParticipantes;

    private String titulo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone = "GMT+8")
    private Date horaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone = "GMT+8")
    private Date horaFin;

    private String idDepuracion;


    @OneToMany(targetEntity = Participante.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "reunion_id", referencedColumnName = "id")
    private List<Participante> participantes;



}
