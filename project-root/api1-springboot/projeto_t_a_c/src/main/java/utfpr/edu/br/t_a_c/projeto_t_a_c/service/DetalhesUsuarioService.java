package utfpr.edu.br.t_a_c.projeto_t_a_c.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Pessoa;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.PessoaRepository;

@Service
public class DetalhesUsuarioService implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email == null || email.isEmpty()) {
            throw new UsernameNotFoundException("Email não fornecido");
        }

        System.out.println("Email recebido: " + email); // Adicionando log para depuração

        Pessoa pessoa = pessoaRepository.findByEmail(email);
        if (pessoa == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }

        return new org.springframework.security.core.userdetails.User(pessoa.getEmail(), pessoa.getSenha(),
                new ArrayList<>());
    }
}
