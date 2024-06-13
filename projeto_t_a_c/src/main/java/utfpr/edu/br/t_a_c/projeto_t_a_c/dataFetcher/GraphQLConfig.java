package utfpr.edu.br.t_a_c.projeto_t_a_c.dataFetcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;

@Configuration
public class GraphQLConfig {

    // @Bean
    // public GraphQLSchema graphQLSchema(PessoaDataFetcher pessoaDataFetcher) {
    // return SchemaParser.newParser()
    // .files("graphql/schema.graphqls")
    // .resolvers(pessoaDataFetcher)
    // .build()
    // .makeExecutableSchema();
    // }

    @Bean
    public GraphQLSchema graphQLSchema(PessoaDataFetcher pessoaDataFetcher) {
        try {
            // Cria e retorna o esquema GraphQL baseado no arquivo de schema e no data
            // fetcher
            return SchemaParser.newParser()
                    .files("graphql/schema.graphqls") // Certifique-se de que este caminho está correto
                    .resolvers(pessoaDataFetcher) // Adiciona o data fetcher
                    .build()
                    .makeExecutableSchema();
        } catch (Exception e) {
            // Loga e lida com exceções de forma apropriada
            System.err.println("Erro ao criar o schema GraphQL: " + e.getMessage());
            throw new RuntimeException("Erro ao criar o schema GraphQL", e);
        }
    }

}
