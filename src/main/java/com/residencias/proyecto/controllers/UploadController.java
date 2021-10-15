package com.residencias.proyecto.controllers;

import com.residencias.proyecto.dto.Reunion;
import com.residencias.proyecto.services.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UploadController {

    @Autowired
    private AsistenciaService asistenciaService;


    @PostMapping("/Prueba")
    public String prueba() {
        return "Prueba";
    }


    @PostMapping("/upload-csv-file")
    public Reunion tomarAsistencia(@RequestBody Reunion reunion, Model model) {
        return asistenciaService.tomarAsistencia(reunion);
        //return reunion;
    }



}
