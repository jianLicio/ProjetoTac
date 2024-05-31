package utfpr.edu.br.t_a_c.projeto_t_a_c.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import utfpr.edu.br.t_a_c.projeto_t_a_c.dto.DispositivoDTO;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Atuador;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Dispositivo;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Gateway;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Sensor;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.AtuadorRepository;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.GatewayRepository;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.SensorRepository;

public class DispositivoMapper {
    public Dispositivo toEntity(
            DispositivoDTO dto,
            GatewayRepository gatewayRepository,
            SensorRepository sensorRepository,
            AtuadorRepository atuadorRepository) {

        Dispositivo dispositivo = new Dispositivo();
        BeanUtils.copyProperties(dto, dispositivo, "gatewayId", "sensorIds", "atuadorIds");

        Gateway gateway = gatewayRepository.findById(dto.gatewayId())
                .orElseThrow(() -> new RuntimeException("Gateway not found"));
        dispositivo.setGateway(gateway);

        List<Sensor> sensores = dto.sensorIds().stream()
                .map(id -> sensorRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Sensor not found")))
                .collect(Collectors.toList());
        dispositivo.setSensores(sensores);

        List<Atuador> atuadores = dto.atuadorIds().stream()
                .map(id -> atuadorRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Atuador not found")))
                .collect(Collectors.toList());
        dispositivo.setAtuadores(atuadores);

        dispositivo.setCriadoEm(LocalDateTime.now());
        dispositivo.setAtualizadoEm(LocalDateTime.now());

        return dispositivo;
    }

    public DispositivoDTO toDTO(Dispositivo dispositivo) {
        return new DispositivoDTO(
                dispositivo.getNome(),
                dispositivo.getDescricao(),
                dispositivo.getLocalizacao(),
                dispositivo.getGateway().getId(),
                dispositivo.getSensores().stream().map(Sensor::getId).collect(Collectors.toList()),
                dispositivo.getAtuadores().stream().map(Atuador::getId).collect(Collectors.toList()),
                dispositivo.getCriadoEm(),
                dispositivo.getAtualizadoEm());
    }

    public void updateEntityFromDTO(DispositivoDTO dto, Dispositivo dispositivo, GatewayRepository gatewayRepository,
            SensorRepository sensorRepository, AtuadorRepository atuadorRepository) {
        BeanUtils.copyProperties(dto, dispositivo, "gatewayId", "sensorIds", "atuadorIds");

        Gateway gateway = gatewayRepository.findById(dto.gatewayId())
                .orElseThrow(() -> new RuntimeException("Gateway not found"));
        dispositivo.setGateway(gateway);

        List<Sensor> sensores = dto.sensorIds().stream()
                .map(id -> sensorRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Sensor not found")))
                .collect(Collectors.toList());
        dispositivo.setSensores(sensores);

        List<Atuador> atuadores = dto.atuadorIds().stream()
                .map(id -> atuadorRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Atuador not found")))
                .collect(Collectors.toList());
        dispositivo.setAtuadores(atuadores);

        dispositivo.setAtualizadoEm(LocalDateTime.now());
    }
}