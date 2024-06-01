package utfpr.edu.br.t_a_c.projeto_t_a_c.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public record PessoaDTO(

        @NotBlank @Size(min = 1, max = 100) String nome,

        @NotBlank @Email String email,

        @NotBlank @Size(min = 8, max = 100) String senha,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dataNascimento,

        @JsonProperty("atualizado_em") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime atualizadoEm) {
}
