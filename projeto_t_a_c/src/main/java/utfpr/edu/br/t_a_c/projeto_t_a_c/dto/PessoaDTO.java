package utfpr.edu.br.t_a_c.projeto_t_a_c.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PessoaDTO(
        String nome,
        String email,
        LocalDate dataNascimento,
        LocalDate criadoEm,
        LocalDateTime updateAt) {

}
