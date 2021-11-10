package com.residencias.proyecto.services;

import com.residencias.proyecto.dto.Participante;
import com.residencias.proyecto.dto.Reunion;
import com.residencias.proyecto.dto.ReunionStatus;
import com.residencias.proyecto.rabbitmq.config.MessagingConfig;
import com.residencias.proyecto.utils.AppProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Part;
import java.util.Date;
import java.util.List;

@Component
public class AsistenciaService {


    @Autowired
    private RabbitTemplate template;

    public Reunion tomarAsistencia(Reunion reunion, boolean revisarAsistencia){

        reunion.setParticipantes(eliminarRepetidos(reunion.getParticipantes()));

        /** Validar tolerancia **/
        reunion.setParticipantes(validarTolerancia(reunion.getParticipantes(), reunion.getHoraInicio()));

        /** Validar tiempo minimo **/
        reunion.setParticipantes(validarTiempoMinimo(reunion.getParticipantes()));


        //Si el usuario desea revisar la asistencia antes de confirmar
        if (revisarAsistencia)  return reunion;

        return confirmarAsistencia(reunion);

        //return reunion;
    }

    public Reunion confirmarAsistencia(Reunion reunion) {
        //Logica para enviar la participación a la cola
        ReunionStatus reunionStatus = new ReunionStatus(reunion, "PROCESS", "order placed successfully");
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, reunionStatus);
        return null;
    }

    public List<Reunion> obtenerLista(){
        return null;
    }


    private List<Participante> validarTiempoMinimo(List<Participante> participantes) {
        int tiempoMinimo = Integer.parseInt(AppProperties.getProperty("tiempo.minimo"));
        int horasMinimas =0;
        while (tiempoMinimo >= 60){
            horasMinimas++;
            tiempoMinimo = tiempoMinimo - 60;
        }
        int minutosMinimo = tiempoMinimo;


        for (int i=0; i<participantes.size(); i++){

            int horasParticipante = participantes.get(i).getHoraSalida().getHours() - participantes.get(i).getHoraUnion().getHours();
            if(horasParticipante < 0){
                horasParticipante = participantes.get(i).getHoraSalida().getHours() - 12 + participantes.get(i).getHoraUnion().getHours();
            }

            int minutosParticipante = participantes.get(i).getHoraSalida().getMinutes() - participantes.get(i).getHoraUnion().getMinutes();
            if (minutosParticipante < 0){
                horasParticipante = horasParticipante - 1;
                minutosParticipante = participantes.get(i).getHoraSalida().getMinutes() + 60 - participantes.get(i).getHoraUnion().getMinutes();
            }

            int segundosParticipante = participantes.get(i).getHoraSalida().getSeconds() - participantes.get(i).getHoraUnion().getSeconds();
            if (segundosParticipante < 0){
                minutosParticipante = minutosParticipante - 1;
                //segundosParticipante = participantes.get(i).getHoraSalida().getSeconds() + 60 - participantes.get(i).getHoraUnion().getSeconds();
            }

            boolean validaParticipacion = horasParticipante >= horasMinimas && minutosParticipante >= minutosMinimo;
            participantes.get(i).setParticipaciónValida(validaParticipacion);


        }
        return participantes;

    }

    private List<Participante> validarTolerancia(List<Participante> participantes, Date horaInicioDate) {
        int minutosTolerancia = Integer.parseInt(AppProperties.getProperty("tiempo.tolerancia"));

        int horaInicio = horaInicioDate.getHours();
        int minutosInicio = horaInicioDate.getMinutes();


        for (int i=0; i<participantes.size(); i++){

            int horaInicioParticipante = participantes.get(i).getHoraUnion().getHours();
            int minutoInicioParticipante = participantes.get(i).getHoraUnion().getMinutes();


            int horasDeTolerancia = horaInicio;
            int minutosDeTolerancia = minutosInicio + minutosTolerancia;
            if (minutosDeTolerancia >= 60){
                horasDeTolerancia = horasDeTolerancia + 1;
                minutosDeTolerancia = minutosDeTolerancia - 60;
            }

            boolean validaParticipacion = horaInicioParticipante < horasDeTolerancia || (horaInicioParticipante == horasDeTolerancia && minutoInicioParticipante <= minutosDeTolerancia);
            participantes.get(i).setParticipaciónValida(validaParticipacion);

        }
        return participantes;

    }

    private List<Participante> eliminarRepetidos(List<Participante> participantes) {
        for (int i=0; i<participantes.size(); i++){
            for (int j=i+1; j<participantes.size() ;j++){
                Participante participanteUno = participantes.get(i);
                Participante participanteDos = participantes.get(j);
                if (participanteUno.getNombreCompleto().equals(participanteDos.getNombreCompleto())){


                    int segundosRecavadosParticipanteUno = participanteUno.getHoraSalida().getSeconds() - participanteUno.getHoraUnion().getSeconds();
                    int minutosRecavadosParticipanteUno = participanteUno.getHoraSalida().getMinutes() - participanteUno.getHoraUnion().getMinutes();
                    if(segundosRecavadosParticipanteUno < 0){
                        segundosRecavadosParticipanteUno = segundosRecavadosParticipanteUno + 60;
                        minutosRecavadosParticipanteUno -= 1;
                    }


                    int segundosRecavadosParticipanteDos = participanteDos.getHoraSalida().getSeconds() - participanteDos.getHoraUnion().getSeconds();
                    int minutosRecavadosParticipanteDos = participanteDos.getHoraSalida().getMinutes() - participanteDos.getHoraUnion().getMinutes();
                    if(segundosRecavadosParticipanteDos < 0){
                        segundosRecavadosParticipanteDos = segundosRecavadosParticipanteDos + 60;
                        minutosRecavadosParticipanteDos -= 1;
                    }

                    int horasRecavadasParticipanteUno = participanteUno.getHoraSalida().getHours() - participanteUno.getHoraUnion().getHours();
                    int horasRecavadasParticipanteDos = participanteDos.getHoraSalida().getHours() - participanteDos.getHoraUnion().getHours();


                    if(minutosRecavadosParticipanteUno < 0){
                        minutosRecavadosParticipanteUno = minutosRecavadosParticipanteUno + 60;
                        horasRecavadasParticipanteUno -= 1;
                    }



                    if(minutosRecavadosParticipanteDos < 0){
                        minutosRecavadosParticipanteDos = minutosRecavadosParticipanteDos + 60;
                        horasRecavadasParticipanteDos -= 1;
                    }


                    int nuevosSegundosParticipante = segundosRecavadosParticipanteUno + segundosRecavadosParticipanteDos;
                    int nuevosMinutosParticpante = minutosRecavadosParticipanteUno + minutosRecavadosParticipanteDos;
                    int nuevasHorasParticipante =   horasRecavadasParticipanteUno + horasRecavadasParticipanteDos;


                    if(nuevosSegundosParticipante >= 60){
                        nuevosMinutosParticpante++;
                        nuevosSegundosParticipante -=60;
                    }

                    if(nuevosMinutosParticpante >=60 ){
                        nuevasHorasParticipante++;
                        nuevosMinutosParticpante -=60;
                    }



                    participantes.get(i).setDuracion(crearDuracion
                                                        (Math.abs(nuevosSegundosParticipante),
                                                        Math.abs(nuevosMinutosParticpante),
                                                        Math.abs(nuevasHorasParticipante)));
                    participantes.get(i).setHoraSalida(participantes.get(j).getHoraSalida());
                    participantes.remove(j);


                }
            }
        }

        return participantes;
    }

    private String crearDuracion(int nuevosSegundosParticipante, int nuevosMinutosParticpante, int nuevasHorasParticipante) {
        String nuevaDuración = nuevasHorasParticipante + " h " + nuevosMinutosParticpante + " min " + nuevosSegundosParticipante + " s";
        System.out.println(nuevaDuración);
        return nuevaDuración;
    }


}
