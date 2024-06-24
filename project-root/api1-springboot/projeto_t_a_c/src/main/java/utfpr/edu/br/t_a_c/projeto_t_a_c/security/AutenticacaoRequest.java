package utfpr.edu.br.t_a_c.projeto_t_a_c.security;

import lombok.Data;

@Data
public class AutenticacaoRequest {
    private String email;
    private String senha;
}
