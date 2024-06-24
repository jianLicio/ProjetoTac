package utfpr.edu.br.t_a_c.projeto_t_a_c.dto;

import java.time.LocalDateTime;
import java.util.List;

public record DispositivoDTO(
                String nome,
                String descricao,
                String localizacao,
                Long gatewayId,
                List<Long> sensorIds,
                List<Long> atuadorIds,
                LocalDateTime criadoEm,
                LocalDateTime atualizadoEm) {
}
