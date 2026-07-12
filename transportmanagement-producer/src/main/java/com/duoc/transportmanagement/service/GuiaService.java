package com.duoc.transportmanagement.service;

import com.duoc.transportmanagement.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GuiaService {

    @Autowired
    private ConsumerClient consumerClient;

    private final RabbitMQGuiaProducer producer;

    public GuiaService(RabbitMQGuiaProducer producer) {
        this.producer = producer;
    }

    public List<GuiaResumenDTO> findAll() {
        return consumerClient.findAllGuias();
    }

    public GuiaDTO findById(Long id) {
        return consumerClient.findGuiaById(id);
    }

    public List<GuiaResumenDTO> findByFecha(LocalDate fecha) {
        return consumerClient.findByFecha(fecha);
    }

    public String createGuia(GuiaCreateDTO dto) {

        GuiaMessageDTO mensaje = new GuiaMessageDTO();
        mensaje.setOperacion("CREATE");
        mensaje.setGuiaCreate(dto);

        producer.sendMessage(mensaje);

        return "Solicitud enviada a RabbitMQ";
    }

    public String updateGuia(Long id, GuiaUpdateDTO dto) {

        GuiaMessageDTO mensaje =
                new GuiaMessageDTO();

        mensaje.setOperacion("UPDATE");
        mensaje.setId(id);
        mensaje.setGuiaUpdate(dto);

        producer.sendMessage(mensaje);

        return "Solicitud enviada a RabbitMQ";
    }

    public String deleteGuia(Long id) {

        GuiaMessageDTO mensaje =
                new GuiaMessageDTO();

        mensaje.setOperacion("DELETE");
        mensaje.setId(id);

        producer.sendMessage(mensaje);

        return "Solicitud enviada a RabbitMQ";
    }

    public List<GuiaResumenDTO> findByTransportista(Long transportistaId){

        return consumerClient.findByTransportista(transportistaId);
    }

    public String generarArchivo(Long id){

        GuiaMessageDTO mensaje =
                new GuiaMessageDTO();

        mensaje.setOperacion("GENERAR_ARCHIVO");
        mensaje.setId(id);

        producer.sendMessage(mensaje);

        return "Solicitud enviada a RabbitMQ";
    }

    public String subirArchivoS3(Long id){

        GuiaMessageDTO mensaje =
                new GuiaMessageDTO();

        mensaje.setOperacion("SUBIR_S3");
        mensaje.setId(id);

        producer.sendMessage(mensaje);

        return "Solicitud enviada a RabbitMQ";
    }

    public String actualizarArchivoS3(Long id, GuiaUpdateDTO dto){


        GuiaMessageDTO mensaje =
                new GuiaMessageDTO();

        mensaje.setOperacion("ACTUALIZAR_S3");
        mensaje.setId(id);
        mensaje.setGuiaUpdate(dto);

        producer.sendMessage(mensaje);

        return "Solicitud enviada a RabbitMQ";
    }

    public String descargarArchivo(Long id){
        GuiaMessageDTO mensaje =
                new GuiaMessageDTO();

        mensaje.setOperacion("DESCARGAR");
        mensaje.setId(id);

        producer.sendMessage(mensaje);

        return "Solicitud enviada a RabbitMQ";
    }

    public String eliminarArchivoS3(Long id){

        GuiaMessageDTO mensaje =
                new GuiaMessageDTO();

        mensaje.setOperacion("ELIMINAR_S3");
        mensaje.setId(id);

        producer.sendMessage(mensaje);

        return "Solicitud enviada a RabbitMQ";
    }
}
