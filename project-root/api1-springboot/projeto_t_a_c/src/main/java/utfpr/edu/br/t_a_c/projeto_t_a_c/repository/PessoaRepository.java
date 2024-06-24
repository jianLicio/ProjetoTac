package utfpr.edu.br.t_a_c.projeto_t_a_c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Pessoa;

@Repository
public interface PessoaRepository
                extends JpaRepository<Pessoa, Long> {
        Pessoa findByEmail(String email);
}
