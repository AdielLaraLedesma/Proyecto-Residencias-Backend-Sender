package com.residencias.proyecto.services;

import com.residencias.proyecto.dto.Participante;
import com.residencias.proyecto.dto.Reunion;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AsistenciaService {

    public Reunion tomarAsistencia(Reunion reunion){
        for (int i=0; i<reunion.getParticipantes().size(); i++){
            for (int j=i+1; j<reunion.getParticipantes().size() ;j++){
                Participante participanteUno = reunion.getParticipantes().get(i);
                Participante participanteDos = reunion.getParticipantes().get(j);


                if (participanteUno.getNombreCompleto().equals(participanteDos.getNombreCompleto())){


                    int horasParticipanteUno = participanteUno.getHoraSalida().getHours() - participanteUno.getHoraUnion().getHours();
                    int minutosParticipanteUno = participanteUno.getHoraSalida().getMinutes() - participanteUno.getHoraUnion().getMinutes();
                    int segundosPartcipanteUno = participanteUno.getHoraSalida().getSeconds() - participanteUno.getHoraUnion().getSeconds();

                    int horasParticipanteDos = participanteDos.getHoraSalida().getHours() - participanteDos.getHoraUnion().getHours();
                    int minutosParticipanteDos = participanteDos.getHoraSalida().getMinutes() - participanteDos.getHoraUnion().getMinutes();
                    int segundosPartcipanteDos = participanteDos.getHoraSalida().getSeconds() - participanteDos.getHoraUnion().getSeconds();


                    System.out.println(horasParticipanteUno);
                    System.out.println(minutosParticipanteUno);
                    System.out.println(segundosPartcipanteUno);

                }
            }
        }


        return reunion;
    }


}
