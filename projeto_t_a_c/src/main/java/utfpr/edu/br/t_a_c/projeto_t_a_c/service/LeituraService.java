package utfpr.edu.br.t_a_c.projeto_t_a_c.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import utfpr.edu.br.t_a_c.projeto_t_a_c.dto.LeituraDTO;
import utfpr.edu.br.t_a_c.projeto_t_a_c.exception.NotFoundException;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Leitura;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.LeituraRepository;

public class LeituraService {
    @Autowired
    private LeituraRepository leituraRepository;

    public Leitura create(LeituraDTO dto) {
        var leitura = new Leitura();
        BeanUtils.copyProperties(dto, leitura);

        return leituraRepository.save(leitura);
    }

    public List<Leitura> getAll() {
        return leituraRepository.findAll();
    }

    public Optional<Leitura> getById(Long id) {
        return leituraRepository.findById(id);
    }

    public Leitura update(long id, LeituraDTO dto) throws NotFoundException {
        var res = leituraRepository.findById(id);
        var leitura = res.get();

        if (res.isEmpty()) {
            throw new NotFoundException("Dispositivo " + id + " não existe.");
        }

        BeanUtils.copyProperties(dto, leitura);

        return leituraRepository.save(leitura);
    }

    public void delete(Long id) throws NotFoundException {
        var res = leituraRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Leitura " + id + " não existe.");
        }

        leituraRepository.delete(res.get());
    }
}
