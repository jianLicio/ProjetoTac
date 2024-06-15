package utfpr.edu.br.t_a_c.projeto_t_a_c.mapper;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import utfpr.edu.br.t_a_c.projeto_t_a_c.dto.LeituraDTO;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Leitura;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Sensor;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.SensorRepository;

@Component
public class LeituraMapper {

        public Leitura toEntity(
                        LeituraDTO dto,
                        SensorRepository sensorRepository) {

                Leitura leitura = new Leitura();

                BeanUtils.copyProperties(
                                dto,
                                leitura,
                                "SensorId",
                                "criadoEm",
                                "atualizadoEm");

                Sensor sensor = sensorRepository.findById(dto.sensorId())
                                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));

                leitura.setSensor(sensor);

                leitura.setCriadoEm(LocalDateTime.now());
                leitura.setAtualizadoEm(LocalDateTime.now());

                return leitura;
        }

        public LeituraDTO toDTO(Leitura leitura) {
                return new LeituraDTO(
                                leitura.getValor(),
                                leitura.getSensor().getId(),
                                leitura.getCriadoEm(),
                                leitura.getAtualizadoEm());
        }

        public void updateEntityFromDTO(
                        LeituraDTO dto,
                        Leitura leitura,
                        SensorRepository sensorRepository) {

                BeanUtils.copyProperties(
                                dto,
                                leitura,
                                "sensorId",
                                "criadoEm",
                                "atualizadoEm");

                Sensor sensor = sensorRepository.findById(
                                dto.sensorId())
                                .orElseThrow(
                                                () -> new RuntimeException("Sensor não encontrado"));
                leitura.setSensor(sensor);

                leitura.setAtualizadoEm(LocalDateTime.now());
        }
}
