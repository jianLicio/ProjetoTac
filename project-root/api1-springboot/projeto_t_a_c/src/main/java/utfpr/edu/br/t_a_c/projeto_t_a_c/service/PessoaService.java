package utfpr.edu.br.t_a_c.projeto_t_a_c.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import utfpr.edu.br.t_a_c.projeto_t_a_c.dto.PessoaDTO;
import utfpr.edu.br.t_a_c.projeto_t_a_c.exception.NotFoundException;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Pessoa;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.PessoaRepository;

@Service
@Component
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // public Pessoa create(PessoaDTO dto) {
    // var pessoa = new Pessoa();
    // BeanUtils.copyProperties(dto, pessoa);
    // pessoa.setSenha(passwordEncoder.encode(dto.senha()));

    // return pessoaRepository.save(pessoa);
    // }

    public Pessoa save(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(dto, pessoa);
        pessoa.setSenha(passwordEncoder.encode(dto.senha()));
        return pessoaRepository.save(pessoa);
    }

    public String encryptPassword(String senha) {
        return passwordEncoder.encode(senha);
    }

    // public Pessoa save(Pessoa pessoa) {
    // pessoa.setSenha(passwordEncoder.encode(pessoa.getSenha()));

    // return pessoaRepository.save(pessoa);
    // }

    public List<Pessoa> getAll() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        System.out.println("Pessoas recuperadas: " + pessoas);
        return pessoas;
        // return pessoaRepository.findAll();
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

        if (dto.senha() != null && !dto.senha().isEmpty()) {
            pessoa.setSenha(passwordEncoder.encode(dto.senha()));
        }

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
