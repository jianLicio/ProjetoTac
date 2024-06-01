package utfpr.edu.br.t_a_c.projeto_t_a_c.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import utfpr.edu.br.t_a_c.projeto_t_a_c.dto.GatewayDTO;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Dispositivo;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Gateway;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Pessoa;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.DispositivoRepository;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.PessoaRepository;

@Component
public class GatewayMapper {
        public Gateway toEntity(
                        GatewayDTO dto,
                        PessoaRepository pessoaRepository,
                        DispositivoRepository dispositivoRepository) {

                Gateway gateway = new Gateway();
                BeanUtils.copyProperties(
                                dto,
                                gateway,
                                "pessoaId",
                                "dispositivoId",
                                "criadoEm",
                                "atualizadoEm");

                Pessoa pessoa = pessoaRepository.findById(dto.pessoaId())
                                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
                gateway.setPessoa(pessoa);

                if (dto.dispositivoId() != null && !dto.dispositivoId().isEmpty()) {
                        List<Dispositivo> dispositivo = dto.dispositivoId().stream()
                                        .map(id -> dispositivoRepository.findById(id)
                                                        .orElseThrow(() -> new RuntimeException(
                                                                        "Dispositivo não encontrado")))
                                        .collect(Collectors.toList());
                        gateway.setDispositivos(dispositivo);
                }

                gateway.setCriadoEm(LocalDateTime.now());
                gateway.setAtualizadoEm(LocalDateTime.now());

                return gateway;
        }

        public GatewayDTO toDTO(Gateway gateway) {
                return new GatewayDTO(
                                gateway.getNome(),
                                gateway.getLocalizacao(),
                                gateway.getPessoa().getId(),
                                gateway.getDispositivos() != null
                                                ? gateway.getDispositivos().stream().map(Dispositivo::getId)
                                                                .collect(Collectors.toList())
                                                : null,
                                gateway.getCriadoEm(),
                                gateway.getAtualizadoEm());
        }

        public void updateEntityFromDTO(
                        GatewayDTO dto,
                        Gateway gateway,
                        PessoaRepository pessoaRepository,
                        DispositivoRepository dispositivoRepository) {

                BeanUtils.copyProperties(
                                dto,
                                gateway,
                                "pessoaId",
                                "dispositivoId",
                                "criadoEm",
                                "atualizadoEm");

                Pessoa pessoa = pessoaRepository.findById(dto.pessoaId())
                                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
                gateway.setPessoa(pessoa);

                if (dto.dispositivoId() != null && !dto.dispositivoId().isEmpty()) {
                        List<Dispositivo> dispositivos = dto.dispositivoId().stream()
                                        .map(id -> dispositivoRepository.findById(id)
                                                        .orElseThrow(() -> new RuntimeException(
                                                                        "Dispositivo não encontrado")))
                                        .collect(Collectors.toList());
                        gateway.setDispositivos(dispositivos);
                }
                gateway.setAtualizadoEm(LocalDateTime.now());
        }
}
