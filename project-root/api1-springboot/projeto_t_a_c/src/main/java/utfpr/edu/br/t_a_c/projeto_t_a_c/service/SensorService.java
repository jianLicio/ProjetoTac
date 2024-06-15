package utfpr.edu.br.t_a_c.projeto_t_a_c.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utfpr.edu.br.t_a_c.projeto_t_a_c.dto.SensorDTO;
import utfpr.edu.br.t_a_c.projeto_t_a_c.exception.NotFoundException;
import utfpr.edu.br.t_a_c.projeto_t_a_c.mapper.SensorMapper;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Sensor;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.DispositivoRepository;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.LeituraRepository;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.SensorRepository;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private LeituraRepository leituraRepository;

    @Autowired
    private SensorMapper sensorMapper;

    public SensorDTO create(SensorDTO dto) {
        Sensor sensor = sensorMapper.toEntity(
                dto,
                dispositivoRepository,
                leituraRepository);

        Sensor criar = sensorRepository.save(sensor);

        return sensorMapper.toDTO(criar);
    }

    public List<Sensor> getAll() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> getById(Long id) {
        return sensorRepository.findById(id);
    }

    public Sensor update(long id, SensorDTO dto) throws NotFoundException {
        Optional<Sensor> res = sensorRepository.findById(id);
        if (res.isEmpty()) {
            throw new NotFoundException("Sensor " + id + " não existe.");
        }

        Sensor sensor = res.get();

        sensorMapper.updateEntityFromDTO(
                dto,
                sensor,
                dispositivoRepository,
                leituraRepository);

        return sensorRepository.save(sensor);
    }

    public void delete(Long id) throws NotFoundException {
        var res = sensorRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Atuador " + id + " não existe.");
        }

        sensorRepository.delete(res.get());
    }
}
