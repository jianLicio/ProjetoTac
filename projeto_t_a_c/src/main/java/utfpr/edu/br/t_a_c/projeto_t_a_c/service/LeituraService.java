package utfpr.edu.br.t_a_c.projeto_t_a_c.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utfpr.edu.br.t_a_c.projeto_t_a_c.dto.LeituraDTO;
import utfpr.edu.br.t_a_c.projeto_t_a_c.exception.NotFoundException;
import utfpr.edu.br.t_a_c.projeto_t_a_c.mapper.LeituraMapper;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Leitura;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.LeituraRepository;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.SensorRepository;

@Service
public class LeituraService {

    @Autowired
    private LeituraRepository leituraRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private LeituraMapper leituraMapper;

    public LeituraDTO create(LeituraDTO dto) {

        Leitura leitura = leituraMapper.toEntity(
                dto,
                sensorRepository);

        Leitura criar = leituraRepository.save(leitura);

        return leituraMapper.toDTO(criar);

    }

    public List<Leitura> getAll() {
        return leituraRepository.findAll();
    }

    public Optional<Leitura> getById(Long id) {
        return leituraRepository.findById(id);
    }

    public Leitura update(long id, LeituraDTO dto) throws NotFoundException {

        Optional<Leitura> res = leituraRepository.findById(id);
        if (res.isEmpty()) {
            throw new NotFoundException("Leitura " + id + " não existe.");
        }

        Leitura leitura = res.get();

        leituraMapper.updateEntityFromDTO(
                dto,
                leitura,
                sensorRepository);

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
