package utfpr.edu.br.t_a_c.projeto_t_a_c.dto;

import java.time.LocalDateTime;

public record AtuadorDTO(
        String tipo,
        String descricao,
        Long dispositivoId,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm) {
}
