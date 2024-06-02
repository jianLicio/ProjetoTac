package utfpr.edu.br.t_a_c.projeto_t_a_c.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import utfpr.edu.br.t_a_c.projeto_t_a_c.dto.PessoaDTO;
import utfpr.edu.br.t_a_c.projeto_t_a_c.exception.NotFoundException;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Pessoa;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.PessoaRepository;

@Service
public class PessoaService {

    private HttpServletResponse response;

    int statusCode = response.getStatus();

    
    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa create(PessoaDTO dto) {
        var pessoa = new Pessoa();
        BeanUtils.copyProperties(dto, pessoa);

        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> getAll() { 
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> getById(long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa update(long id, PessoaDTO dto) throws NotFoundException {
        var res = pessoaRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Pessoa " + id + " não existe.");
        }

        var pessoa = res.get();
        pessoa.setNome(dto.nome());
        pessoa.setEmail(dto.email());

        return pessoaRepository.save(pessoa);
    }

    public void delete(long id) throws NotFoundException {
        var res = pessoaRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Pessoa " + id + " não existe.");
        }

        pessoaRepository.delete(res.get());
    }

}
