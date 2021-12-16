package com.residencias.proyecto.controllers;

import com.residencias.proyecto.dto.Participante;
import com.residencias.proyecto.dto.Reunion;
import com.residencias.proyecto.dto.dtoPrueba;
import com.residencias.proyecto.services.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UploadController {

    @Autowired
    private AsistenciaService asistenciaService;


    @PostMapping(value = "/revisarAsistencia", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Reunion> tomarAsistencia(@RequestBody Reunion reunion, @RequestParam boolean revisarAsistencia) {
        return asistenciaService.tomarAsistencia(reunion, revisarAsistencia);
    }

    @PostMapping(value = "/confirmarAsistencia", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Reunion> conformarAsistencia(@RequestBody Reunion reunion) {
        return asistenciaService.confirmarAsistencia(reunion);
    }

    @PostMapping(value = "/obtener-reuniones", produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<Reunion>> obtenerReuniones(){

        return ResponseEntity.ok(asistenciaService.obtenerReuniones());
    }

    @PostMapping(value = "/obtener-reunion/{idReunion}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Reunion> obtenerReuniones(@RequestParam Integer idReunion){
        return asistenciaService.obtenerReunion(idReunion);
    }



}
