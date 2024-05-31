package utfpr.edu.br.t_a_c.projeto_t_a_c.dto;

import java.time.LocalDateTime;

public record CriarPessoaDTO(
        String nome,
        String email,
        String senha,
        LocalDateTime dataNascimento,
        LocalDateTime updateAt) {
}
