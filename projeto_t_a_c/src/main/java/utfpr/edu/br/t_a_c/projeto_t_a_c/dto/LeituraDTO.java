package utfpr.edu.br.t_a_c.projeto_t_a_c.dto;

import java.time.LocalDateTime;

public record LeituraDTO(
        String valor,
        Long sensorId,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm) {

}
