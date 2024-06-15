package utfpr.edu.br.t_a_c.projeto_t_a_c.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utfpr.edu.br.t_a_c.projeto_t_a_c.dto.DispositivoDTO;
import utfpr.edu.br.t_a_c.projeto_t_a_c.exception.NotFoundException;
import utfpr.edu.br.t_a_c.projeto_t_a_c.mapper.DispositivoMapper;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Dispositivo;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.AtuadorRepository;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.DispositivoRepository;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.GatewayRepository;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.SensorRepository;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private GatewayRepository gatewayRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private AtuadorRepository atuadorRepository;

    @Autowired
    private DispositivoMapper dispositivoMapper;

    public DispositivoDTO create(DispositivoDTO dto) {

        Dispositivo dispositivo = dispositivoMapper.toEntity(
                dto,
                gatewayRepository,
                sensorRepository,
                atuadorRepository);
        Dispositivo savedDispositivo = dispositivoRepository.save(dispositivo);

        return dispositivoMapper.toDTO(savedDispositivo);

    }

    public List<Dispositivo> getAll() {
        return dispositivoRepository.findAll();
    }

    public Optional<Dispositivo> getById(long id) {
        return dispositivoRepository.findById(id);
    }

    public Dispositivo update(long id, DispositivoDTO dto) throws NotFoundException {
        Optional<Dispositivo> res = dispositivoRepository.findById(id);
        if (res.isEmpty()) {
            throw new NotFoundException("Dispositivo " + id + " não existe.");
        }

        Dispositivo dispositivo = res.get();
        dispositivoMapper.updateEntityFromDTO(
            dto,
                dispositivo,
                gatewayRepository,
                sensorRepository,
                atuadorRepository);

        return dispositivoRepository.save(dispositivo);

    }

    public void delete(Long id) throws NotFoundException {
        var res = dispositivoRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Atuador " + id + " não existe.");
        }

        dispositivoRepository.delete(res.get());
    }

}
