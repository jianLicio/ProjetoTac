package utfpr.edu.br.t_a_c.projeto_t_a_c.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import utfpr.edu.br.t_a_c.projeto_t_a_c.dto.SensorDTO;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Dispositivo;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Leitura;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Sensor;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.DispositivoRepository;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.LeituraRepository;

@Component
public class SensorMapper {

        public Sensor toEntity(
                        SensorDTO dto,
                        DispositivoRepository dispositivoRepository,
                        LeituraRepository leituraRepository) {

                Sensor sensor = new Sensor();
                BeanUtils.copyProperties(
                                dto,
                                sensor,
                                "dispositivoId",
                                "leiturasId",
                                "criadoEm",
                                "atualizadoEm");

                Dispositivo dispositivo = dispositivoRepository.findById(dto.dispositivoId())
                                .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado"));
                sensor.setDispositivo(dispositivo);

                if (dto.leiturasId() != null && !dto.leiturasId().isEmpty()) {
                        List<Leitura> leitura = dto.leiturasId().stream()
                                        .map(id -> leituraRepository.findById(id)
                                                        .orElseThrow(
                                                                        () -> new RuntimeException(
                                                                                        "Leitura não encontrada")))
                                        .collect(Collectors.toList());
                        sensor.setLeituras(leitura);
                }

                sensor.setCriadoEm(LocalDateTime.now());
                sensor.setAtualizadoEm(LocalDateTime.now());

                return sensor;
        }

        public SensorDTO toDTO(Sensor leitura) {
                return new SensorDTO(
                                leitura.getTipo(),
                                leitura.getDescricao(),
                                leitura.getDispositivo().getId(),

                                leitura.getLeituras() != null ? leitura
                                                .getLeituras()
                                                .stream()
                                                .map(Leitura::getId).collect(Collectors.toList())
                                                : null,

                                leitura.getCriadoEm(),
                                leitura.getAtualizadoEm());
        }

        public void updateEntityFromDTO(
                        SensorDTO dto,
                        Sensor sensor,
                        DispositivoRepository dispositivoRepository,
                        LeituraRepository leituraRepository) {

                BeanUtils.copyProperties(
                                dto,
                                sensor,
                                "dispositivoId",
                                "leiturasId",
                                "criadoEm",
                                "atualizadoEm");

                Dispositivo dispositivo = dispositivoRepository.findById(dto.dispositivoId())
                                .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado"));
                sensor.setDispositivo(dispositivo);

                if (dto.leiturasId() != null && !dto.leiturasId().isEmpty()) {
                        List<Leitura> leitura = dto.leiturasId().stream()
                                        .map(id -> leituraRepository.findById(id)
                                                        .orElseThrow(
                                                                        () -> new RuntimeException(
                                                                                        "Leitura não encontrada")))
                                        .collect(Collectors.toList());
                        sensor.setLeituras(leitura);
                }

                sensor.setAtualizadoEm(LocalDateTime.now());
        }
}
