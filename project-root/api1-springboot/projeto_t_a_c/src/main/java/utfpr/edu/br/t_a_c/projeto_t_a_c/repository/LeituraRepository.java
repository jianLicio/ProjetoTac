package utfpr.edu.br.t_a_c.projeto_t_a_c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Leitura;

@Repository
public interface LeituraRepository extends JpaRepository<Leitura, Long>{

}
