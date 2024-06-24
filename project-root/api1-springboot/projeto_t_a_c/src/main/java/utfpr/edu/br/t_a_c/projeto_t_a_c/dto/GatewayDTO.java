package utfpr.edu.br.t_a_c.projeto_t_a_c.dto;

import java.time.LocalDateTime;
import java.util.List;

public record GatewayDTO(
                String nome,
                String localizacao,
                Long pessoaId,
                List<Long> dispositivoId,
                LocalDateTime criadoEm,
                LocalDateTime atualizadoEm) {
}
