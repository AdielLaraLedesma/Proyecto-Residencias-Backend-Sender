package com.residencias.proyecto.controllers;

import com.residencias.proyecto.dto.Reunion;
import com.residencias.proyecto.dto.dtoPrueba;
import com.residencias.proyecto.services.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UploadController {

    @Autowired
    private AsistenciaService asistenciaService;


    @PostMapping(value = "/Prueba", produces = "application/json", consumes = "application/json")
    public dtoPrueba prueba(@RequestBody dtoPrueba reunion) {

        System.out.println(reunion);
        return reunion;
    }


    @PostMapping("/revisarAsistencia")
    public Reunion tomarAsistencia(@RequestBody Reunion reunion, @RequestParam boolean revisarAsistencia) {
        return asistenciaService.tomarAsistencia(reunion, revisarAsistencia);
    }

    @PostMapping("/confirmarAsistencia")
    public Reunion tomarAsistencia(@RequestBody Reunion reunion) {
        return asistenciaService.confirmarAsistencia(reunion);
    }

    @PostMapping("/obtener-reuniones")
    public List<Reunion> obtenerReuniones(){
        return null;
    }



}
