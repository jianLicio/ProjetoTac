package utfpr.edu.br.t_a_c.projeto_t_a_c.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Pessoa;

@Repository
public interface PessoaRepository
                extends JpaRepository<Pessoa, Long> {
        Pessoa findByEmail(String email);
        
        @Override
        @NonNull
        List<Pessoa> findAll();
}
