package utfpr.edu.br.t_a_c.projeto_t_a_c.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utfpr.edu.br.t_a_c.projeto_t_a_c.dto.GatewayDTO;
import utfpr.edu.br.t_a_c.projeto_t_a_c.exception.NotFoundException;
import utfpr.edu.br.t_a_c.projeto_t_a_c.mapper.GatewayMapper;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Gateway;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.DispositivoRepository;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.GatewayRepository;
import utfpr.edu.br.t_a_c.projeto_t_a_c.repository.PessoaRepository;

@Service
public class GatewayService {

    @Autowired
    private GatewayRepository gatewayRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private GatewayMapper gatewayMapper;

    public GatewayDTO create(GatewayDTO dto) {
        Gateway gateway = gatewayMapper.toEntity(
                dto,
                pessoaRepository,
                dispositivoRepository);
        
                Gateway criar = gatewayRepository.save(gateway);

        return gatewayMapper.toDTO(criar);
    }

    public List<Gateway> getAll() {
        return gatewayRepository.findAll();
    }

    public Optional<Gateway> getById(Long id) {
        return gatewayRepository.findById(id);
    }

    public Gateway update(long id, GatewayDTO dto) throws NotFoundException {
        Optional<Gateway> res = gatewayRepository.findById(id);
        if (res.isEmpty()) {
            throw new NotFoundException("Gateway " + id + " não existe.");
        }

        Gateway gateway = res.get();

        gatewayMapper.updateEntityFromDTO(
                dto,
                gateway,
                pessoaRepository,
                dispositivoRepository);

        return gatewayRepository.save(gateway);

    }

    public void delete(Long id) throws NotFoundException {
        var res = gatewayRepository.findById(id);

        if (res.isEmpty()) {
            throw new NotFoundException("Gateway " + id + " não existe.");
        }

        gatewayRepository.delete(res.get());
    }

}
