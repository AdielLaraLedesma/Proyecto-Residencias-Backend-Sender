package com.residencias.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReunionStatus {

    private Reunion reunion;
    private String status; //Progress, Completed, Failed
    private String message;

}
