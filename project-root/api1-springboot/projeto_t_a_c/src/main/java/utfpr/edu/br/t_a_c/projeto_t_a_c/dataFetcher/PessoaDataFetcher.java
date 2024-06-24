package utfpr.edu.br.t_a_c.projeto_t_a_c.dataFetcher;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import utfpr.edu.br.t_a_c.projeto_t_a_c.dto.PessoaDTO;
import utfpr.edu.br.t_a_c.projeto_t_a_c.exception.NotFoundException;
import utfpr.edu.br.t_a_c.projeto_t_a_c.model.Pessoa;
import utfpr.edu.br.t_a_c.projeto_t_a_c.service.PessoaService;

@Component
public class PessoaDataFetcher implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private PessoaService pessoaService;

    public PessoaDataFetcher() {
        System.out.println("PessoaDataFetcher instanciado");
    }

    public List<Pessoa> pessoas() {
        System.out.println("Chamando o método pessoas no resolver");
        List<Pessoa> result = pessoaService.getAll();
        System.out.println("Resultado: " + result);
        return result;
    }

    public Pessoa pessoa(long id) {
        Optional<Pessoa> pessoa = pessoaService.getById(id);
        System.out.println("Pessoa encontrada: " + pessoa.orElse(null));
        return pessoa.orElse(null);
    }

    // public Pessoa createPessoa(PessoaDTO dto) {

    //     System.out.println("Chamando o método createPessoa no resolver");
    //     Pessoa createdPessoa = pessoaService.create(dto);
    //     System.out.println("Pessoa created: " + createdPessoa); // Add logging
    //     return createdPessoa;
    // }

    public Pessoa updatePessoa(long id, PessoaDTO dto) throws NotFoundException {
        System.out.println("Chamando o método updatePessoa no resolver");
        Pessoa updatedPessoa = pessoaService.update(id, dto);
        System.out.println("Pessoa atualizada: " + updatedPessoa);
        return updatedPessoa;
    }

    public String deletePessoa(long id) throws NotFoundException {
        System.out.println("Chamando o método deletePessoa no resolver");
        pessoaService.delete(id);
        System.out.println("Pessoa deletada com sucesso!");
        return "Pessoa deletada com sucesso!";
    }

}
