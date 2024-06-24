package utfpr.edu.br.t_a_c.projeto_t_a_c.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utfpr.edu.br.t_a_c.projeto_t_a_c.dto.AtuadorDTO;
import utfpr.edu.br.t_a_c.projeto_t_a_c.exception.NotFoundException;
import utfpr.edu.br.t_a_c.projeto_t_a_c.mapper.AtuadorMapper;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Atuador;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.AtuadorRepository;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.DispositivoRepository;

@Service
public class AtuadorService {

    @Autowired
    private AtuadorRepository atuadorRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private AtuadorMapper atuadorMapper;

    public AtuadorDTO create(AtuadorDTO dto) {
        Atuador atuador = atuadorMapper.toEntity(
                dto,
                dispositivoRepository);

        Atuador criar = atuadorRepository.save(atuador);

        return atuadorMapper.toDTO(criar);
    }

    public List<Atuador> getAll() {
        return atuadorRepository.findAll();
    }

    public Optional<Atuador> getById(Long id) {
        return atuadorRepository.findById(id);
    }

    public Atuador update(long id, AtuadorDTO dto) throws NotFoundException {
        Optional<Atuador> res = atuadorRepository.findById(id);
        if (res.isEmpty()) {
            throw new NotFoundException("Atuador " + id + " não existe.");
        }

        Atuador atuador = res.get();

        atuadorMapper.updateEntityFromDTO(
                dto,
                atuador,
                dispositivoRepository);

        return atuadorRepository.save(atuador);
    }

    public void delete(Long id) throws NotFoundException {
        var res = atuadorRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Atuador " + id + " não existe.");
        }

        atuadorRepository.delete(res.get());
    }
}
