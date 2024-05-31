package utfpr.edu.br.t_a_c.projeto_t_a_c.mapper;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import utfpr.edu.br.t_a_c.projeto_t_a_c.dto.AtuadorDTO;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Atuador;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Dispositivo;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.DispositivoRepository;

@Component
public class AtuadorMapper {

        public Atuador toEntity(
                        AtuadorDTO dto,
                        DispositivoRepository dispositivoRepository) {

                Atuador atuador = new Atuador();
                BeanUtils.copyProperties(
                                dto,
                                atuador,
                                "dispositivoId");

                Dispositivo dispositivo = dispositivoRepository.findById(dto.dispositivoId())
                                .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado"));
                atuador.setDispositivo(dispositivo);

                atuador.setCriadoEm(LocalDateTime.now());
                atuador.setAtualizadoEm(LocalDateTime.now());

                return atuador;
        }

        public AtuadorDTO toDTO(Atuador atuador) {
                return new AtuadorDTO(
                                atuador.getTipo(),
                                atuador.getDescricao(),
                                atuador.getDispositivo().getId(),
                                atuador.getCriadoEm(),
                                atuador.getAtualizadoEm());
        }

        public void updateEntityFromDTO(
                        AtuadorDTO dto,
                        Atuador atuador,
                        DispositivoRepository dispositivoRepository) {
                BeanUtils.copyProperties(
                                dto,
                                atuador,
                                "dispositivoId");

                Dispositivo dispositivo = dispositivoRepository.findById(dto.dispositivoId())
                                .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado"));
                atuador.setDispositivo(dispositivo);

                atuador.setAtualizadoEm(LocalDateTime.now());
        }
}
