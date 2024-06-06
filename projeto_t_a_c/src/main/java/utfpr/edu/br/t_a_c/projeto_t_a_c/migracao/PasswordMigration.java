package utfpr.edu.br.t_a_c.projeto_t_a_c.migracao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Pessoa;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.PessoaRepository;

@Component
public class PasswordMigration {

   @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void migratePasswords() {
        List<Pessoa> pessoas = pessoaRepository.findAll();

        for (Pessoa pessoa : pessoas) {
            if (!pessoa.isEncrypted()) {
                pessoa.setSenha(passwordEncoder.encode(pessoa.getSenha()));
                pessoa.setEncrypted(true);
                pessoaRepository.save(pessoa);
            }
        }
    }
}
