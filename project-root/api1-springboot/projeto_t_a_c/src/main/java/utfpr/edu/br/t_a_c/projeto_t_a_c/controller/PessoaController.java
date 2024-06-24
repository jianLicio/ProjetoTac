package utfpr.edu.br.t_a_c.projeto_t_a_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utfpr.edu.br.t_a_c.projeto_t_a_c.dto.PessoaDTO;
import utfpr.edu.br.t_a_c.projeto_t_a_c.exception.NotFoundException;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Pessoa;
import utfpr.edu.br.t_a_c.projeto_t_a_c.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    // @Autowired
    // private EventoService eventoService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PessoaDTO dto) {
        try {
            // Salvar a nova pessoa
            Pessoa novaPessoa = pessoaService.save(dto);
            // eventoService.emitirEvento("POST", "/pessoa", novaPessoa);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // @PostMapping
    // public ResponseEntity<Object> create(@Argument @RequestBody PessoaDTO dto) {
    // try {
    // var res = pessoaService.create(dto);

    // eventoService.emitirEvento("POST", "/pessoa", res);

    // return ResponseEntity.status(HttpStatus.CREATED).body(res);
    // } catch (Exception ex) {
    // return ResponseEntity.badRequest().body(ex.getMessage());
    // }
    // }

    @GetMapping
    public List<Pessoa> getAll(Authentication authentication) {
        return pessoaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") long id, Authentication authentication) {
        var pessoa = pessoaService.getById(id);

        return pessoa.isPresent()
                ? ResponseEntity.ok().body(pessoa.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id,
            @RequestBody PessoaDTO dto) {

        try {

            // var updatedPessoa = pessoaService.update(id, dto);
            // eventoService.emitirEvento("PUT", "/pessoa/" + id, updatedPessoa);
            return ResponseEntity.ok().body(pessoaService.update(id, dto));

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        try {
            pessoaService.delete(id);
            return ResponseEntity.ok("Pessoa deletada com sucesso!");

        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

        }
    }
}
