package com.residencias.proyecto.services;

import com.residencias.proyecto.dto.Participante;
import com.residencias.proyecto.dto.Reunion;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class AsistenciaService {

    public Reunion tomarAsistencia(Reunion reunion){

        reunion.setParticipantes(validarRepetidos(reunion.getParticipantes()));


        return reunion;
    }

    private List<Participante> validarRepetidos(List<Participante> participantes) {
        for (int i=0; i<participantes.size(); i++){
            for (int j=i+1; j<participantes.size() ;j++){
                Participante participanteUno = participantes.get(i);
                Participante participanteDos = participantes.get(j);
                if (participanteUno.getNombreCompleto().equals(participanteDos.getNombreCompleto())){


                    int nuevasHorasParticipante =   (participanteUno.getHoraSalida().getHours() - participanteUno.getHoraUnion().getHours()) +
                            (participanteDos.getHoraSalida().getHours() - participanteDos.getHoraUnion().getHours());
                    int nuevosMinutosParticpante =  (participanteUno.getHoraSalida().getMinutes() - participanteUno.getHoraUnion().getMinutes()) +
                            (participanteDos.getHoraSalida().getMinutes() - participanteDos.getHoraUnion().getMinutes());
                    int nuevosSegundosParticipante =(participanteUno.getHoraSalida().getSeconds() - participanteUno.getHoraUnion().getSeconds()) +
                            (participanteDos.getHoraSalida().getSeconds() - participanteDos.getHoraUnion().getSeconds());

                    if(nuevosSegundosParticipante >= 60){
                        nuevosMinutosParticpante++;
                        nuevosSegundosParticipante -=60;
                    }

                    if(nuevosMinutosParticpante >=60 ){
                        nuevasHorasParticipante++;
                        nuevosMinutosParticpante -=60;
                    }


                    System.out.println(nuevasHorasParticipante);
                    System.out.println(nuevosMinutosParticpante);
                    System.out.println(nuevosSegundosParticipante);

                    participantes.get(i).setDuracion(crearDuracion(nuevosSegundosParticipante, nuevosMinutosParticpante, nuevasHorasParticipante));
                    participantes.get(i).setHoraSalida(participantes.get(j).getHoraSalida());
                    participantes.remove(j);

                    System.out.println(participantes);

                }
            }
        }

        return null;
    }

    private String crearDuracion(int nuevosSegundosParticipante, int nuevosMinutosParticpante, int nuevasHorasParticipante) {
        String nuevaDuración = nuevasHorasParticipante + " hrs " + nuevosMinutosParticpante + " min " + nuevosSegundosParticipante + " seg ";
        System.out.println(nuevaDuración);
        return nuevaDuración;
    }


}
