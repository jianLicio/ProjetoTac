package utfpr.edu.br.t_a_c.projeto_t_a_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utfpr.edu.br.t_a_c.projeto_t_a_c.dto.LeituraDTO;
import utfpr.edu.br.t_a_c.projeto_t_a_c.exception.NotFoundException;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Leitura;
import utfpr.edu.br.t_a_c.projeto_t_a_c.service.LeituraService;

@RestController
@RequestMapping("/leitura")
public class LeituraController {
    @Autowired
    private LeituraService leituraService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody LeituraDTO dto) {
        try {
            var res = leituraService.create(dto);

            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch (Exception ex) {

            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public List<Leitura> getAll() {
        return leituraService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") long id) {
        var leitura = leituraService.getById(id);

        return leitura.isPresent()
                ? ResponseEntity.ok().body(leitura.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id,
            @RequestBody LeituraDTO dto) {
        try {
            return ResponseEntity.ok().body(leituraService.update(id, dto));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        try {
            leituraService.delete(id);
            return ResponseEntity.ok("Leitura deletado com sucesso!");

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
            
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
