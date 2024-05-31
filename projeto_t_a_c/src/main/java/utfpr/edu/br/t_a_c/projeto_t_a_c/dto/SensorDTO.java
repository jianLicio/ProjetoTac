package utfpr.edu.br.t_a_c.projeto_t_a_c.dto;

import java.time.LocalDateTime;
import java.util.List;

public record SensorDTO(
                String tipo,
                String descricao,
                Long dispositivoId,
                List<Long> leiturasId,
                LocalDateTime criadoEm,
                LocalDateTime atualizadoEm) {
}
 